package ioc.framework;

import ioc.framework.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class BeanFactory {
    private final Map<Class<?>, Object> beans = new ConcurrentHashMap<>();

    static BeanFactory from(Loader loader) {
        BeanFactory beanFactory = new BeanFactory();
        Collection<Class<?>> components = loader.loadComponents();
        components.forEach(beanFactory::putBean);
        components.forEach(beanFactory::autowire);
        return beanFactory;
    }

    private void putBean(Class<?> clazz) {
        try {
            beans.put(clazz, clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void autowire(Class<?> clazz) {
        Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.getAnnotation(Autowired.class) != null)
                .forEach(field -> setField(clazz, field));
    }

    private void setField(Class<?> clazz, Field field) {
        field.setAccessible(true);
        try {
            field.set(getBean(clazz), getBean(field.getType()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    Object getBean(Class<?> clazz) {
        return beans.get(clazz);
    }
}
