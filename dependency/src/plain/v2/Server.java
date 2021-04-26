package plain.v2;

public class Server {
    public void run() {
        Socket socket = Socket.connect(); // (1) 손님을 테이블에 안내한다.
        String request = socket.readRequest(); // (2) 주문을 받는다.

        // (3) 주문을 주방에 전달 후 서빙한다.
        if (request.equals("GET /pizza")) {
            Gordon chef = new Gordon();
            Pizza pizza = chef.createPizza();
            String response = pizza.serialize();
            socket.writeResponse(response);
        } else {
            socket.writeResponse("잘못된 요청입니다.");
        }

        // HTTP Connectionless
        socket.close(); // (4) 손님이 떠난 후 테이블을 청소한다.
    }
}
