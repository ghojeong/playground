package ioc.framework;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ReflectionUtils {
    
    public static List<Class<?>> getAllControllers() {
        Reflections reflections = new Reflections((new ConfigurationBuilder()).setUrls(ClasspathHelper.forPackage(ROOT_PACAKAGE_NAME)).filterInputsBy((new FilterBuilder()).includePackage(ROOT_PACAKAGE_NAME)));
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        log.info("Controller classes : {}", classes);
        return new ArrayList<>(classes);
    }
}
