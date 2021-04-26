package ioc.framework;

import ioc.framework.annotation.Controller;
import ioc.framework.annotation.GetMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Handler {
    private final Map<String, Class<?>> targets = new ConcurrentHashMap<>();
    private final Map<String, Method> handlers = new ConcurrentHashMap<>();

    void put(Class<?> controller) {
        String controllerValue = controller.getAnnotation(Controller.class).value();
        for (Method method : controller.getDeclaredMethods()) {
            String mappingValue = method.getAnnotation(GetMapping.class).value();
            String request = String.format("GET %s%s", controllerValue, mappingValue);
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
