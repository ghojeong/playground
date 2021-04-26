package ioc.framework;

import ioc.framework.annotation.Controller;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ReflectionUtils {
    private static final String ROOT_PACAKAGE_NAME = "user";

    public static List<Class<?>> getAllControllers() {
        Reflections reflections = new Reflections((new ConfigurationBuilder())
                .setUrls(ClasspathHelper.forPackage(ROOT_PACAKAGE_NAME))
                .filterInputsBy((new FilterBuilder())
                        .includePackage(ROOT_PACAKAGE_NAME)));
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        classes.forEach(clazz-> System.out.print(clazz.getName() + " "));
        return new ArrayList<>(classes);
    }
}
