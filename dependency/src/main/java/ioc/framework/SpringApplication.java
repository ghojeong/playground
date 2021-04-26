package ioc.framework;

public class SpringApplication {
    public static void run(Class<?> primarySource, String... args) {
        ApplicationContext context = ApplicationContext.from(primarySource);

        run(context);

//        while (true) {
//            run(context);
//        }
    }

    private static void run(ApplicationContext context) {
        Socket socket = Socket.connect();
        String request = socket.readRequest();
        String response = context.getResponse(request);
        socket.writeResponse(response);
        socket.close();
    }
}
