package ioc.framework;

class ApplicationContext {
    private final BeanFactory beanFactory;
    private final Handler handler = new Handler();

    private ApplicationContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    static ApplicationContext from(Class<?> primarySource) {
        String rootPackage = primarySource.getPackage().getName();
        Loader loader = Loader.from(rootPackage);
        BeanFactory beanFactory = BeanFactory.from(loader);
        ApplicationContext context = new ApplicationContext(beanFactory);
        loader.loadControllers().forEach(context.handler::put);
        return context;
    }

    String getResponse(String request, Object... objects) {
        return handler.handle(beanFactory, request, objects);
    }
}
