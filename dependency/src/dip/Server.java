package dip;

public class Server {
    public void run() {
        Socket socket = Socket.connect();
        String request = socket.readRequest();

        if (request.equals("GET /pizza")) {
            GordonRamsay chef = new GordonRamsay();
            Pizza pizza = chef.createPizza();
            socket.writeResponse(pizza.serialize());
        } else if (request.equals("GET /pasta")) {
            GordonRamsay chef = new GordonRamsay();
            Pasta pasta = chef.createPasta();
            socket.writeResponse(pasta.serialize());
        } else if (request.equals("GET /steak")) {
            GordonRamsay chef = new GordonRamsay();
            Steak steak = chef.createSteak();
            socket.writeResponse(steak.serialize());
        } else {
            socket.writeResponse("잘못된 요청입니다.");
        }

        socket.close(); // HTTP Connectionless
    }
}
