package dip.v2;

public class RestaurantApplication {
    public static void main(String[] args) {
        Server server = new Server();
        while (true) {
            server.run();
        }
    }
}
