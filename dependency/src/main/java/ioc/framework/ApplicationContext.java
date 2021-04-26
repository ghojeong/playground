package ioc.framework;

import ioc.controller.PyroController;

class ApplicationContext {
    private final BeanFactory beanFactory;

    private ApplicationContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    static ApplicationContext from(Class<?> primarySource) {
        String rootPackage = primarySource.getPackage().getName();
        Loader loader = Loader.from(rootPackage);
        BeanFactory beanFactory = BeanFactory.from(loader);
        return new ApplicationContext(beanFactory);
    }

    String getResponse(String request) {
//        return "잘못된 요청입니다.";
        return ((PyroController) beanFactory.getBean(PyroController.class)).getPasta().serialize();
    }
}
