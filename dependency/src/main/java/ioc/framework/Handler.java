package ioc.framework;

import ioc.framework.annotation.Controller;
import ioc.framework.annotation.RequestMapping;
import ioc.framework.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Handler {
    private final Map<String, Class<?>> targets = new ConcurrentHashMap<>();
    private final Map<String, Method> handlers = new ConcurrentHashMap<>();

    void put(Class<?> controller) {
        for (Method method : controller.getDeclaredMethods()) {
            put(controller, method);
        }
    }

    private void put(Class<?> controller, Method method) {
        String path = controller.getAnnotation(Controller.class).value();
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping == null) {
            return;
        }
        for (RequestMethod requestMethod : requestMapping.method()) {
            String httpMethod = requestMethod.name();
            String endpoint = requestMapping.value();
            String request = String.format("%s %s%s", httpMethod, path, endpoint);
            targets.put(request, controller);
            handlers.put(request, method);
        }
    }

    String handle(BeanFactory beanFactory, String request, Object... objects) {
        String defaultResponse = "잘못된 요청입니다.";
        Class<?> targetClass = targets.get(request);
        if (targetClass == null) {
            return defaultResponse;
        }
        Object targetBean = beanFactory.getBean(targetClass);
        Method handler = handlers.get(request);
        try {
            return handler.invoke(targetBean, objects).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return defaultResponse;
    }
}
