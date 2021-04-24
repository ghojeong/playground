package dip;

public class RestaurantApplication {
    public static void main(String[] args) {
        // 식당에서 우리가 웨이터 혹은 서버라 부르는 것
        // 알바생을 뽑아서 24시간 뺑이를 친다.
        Server server = new Server();
        while (true) {
            server.run();
        }
    }
}
