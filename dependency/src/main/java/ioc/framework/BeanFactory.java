package ioc.framework;

import java.util.Map;

class BeanFactory {
    private Map<String, Object> beans;

    Object getBean(String name) {
        return beans.get(name);
    }
}
