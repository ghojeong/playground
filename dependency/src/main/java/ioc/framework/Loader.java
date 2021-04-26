package ioc.framework;

import ioc.framework.annotation.Component;
import ioc.framework.annotation.Controller;
import ioc.framework.annotation.Service;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.Set;
import java.util.stream.Collectors;

class Loader {
    private final Reflections reflections;

    private Loader(Reflections reflections) {
        this.reflections = reflections;
    }

    static Loader from(String rootPackage) {
        ConfigurationBuilder config = new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(rootPackage))
                .filterInputsBy(new FilterBuilder().includePackage(rootPackage));
        Reflections reflections = new Reflections(config);
        return new Loader(reflections);
    }

    Set<Class<?>> loadControllers() {
        return reflections.getTypesAnnotatedWith(Controller.class);
    }

    Set<Class<?>> loadServices() {
        return reflections.getTypesAnnotatedWith(Service.class);
    }

    Set<Class<?>> loadComponents() {
        return reflections.getTypesAnnotatedWith(Component.class)
                .stream()
                .filter(clazz -> !clazz.isInterface())
                .collect(Collectors.toSet());
    }
}
