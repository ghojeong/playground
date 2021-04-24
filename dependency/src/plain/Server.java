package plain;

public class Server {
    public void run() {
        Socket socket = Socket.connect();
        String request = socket.readRequest();

        if (request.equals("GET /pizza")) {
            GordonRamsay chef = new GordonRamsay();
            Pizza pizza = chef.createPizza();
            socket.writeResponse(pizza.serialize());
        } else {
            socket.writeResponse("잘못된 요청입니다.");
        }

        socket.close(); // HTTP Connectionless
    }
}
