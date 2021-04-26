package dip.v2;

import dip.v2.domain.chef.Chef;
import dip.v2.domain.chef.Gordon;
import dip.v2.domain.chef.Honux;
import dip.v2.domain.chef.Pyro;
import dip.v2.domain.food.Food;

public class Server {
    public void run() {
        Socket socket = Socket.connect();
        String request = socket.readRequest();

        if (request.equals("GET /gordon/pizza")) {
            Chef chef = new Gordon();
            Food food = chef.createPizza();
            socket.writeResponse(ResponseDto.from(chef, food).serialize());
        } else if (request.equals("GET /gordon/pasta")) {
            Chef chef = new Gordon();
            Food food = chef.createPasta();
            socket.writeResponse(ResponseDto.from(chef, food).serialize());
        } else if (request.equals("GET /gordon/steak")) {
            Chef chef = new Gordon();
            Food food = chef.createSteak();
            socket.writeResponse(ResponseDto.from(chef, food).serialize());
        } else if (request.equals("GET /honux/pizza")) {
            Chef chef = new Honux();
            Food food = chef.createPizza();
            socket.writeResponse(ResponseDto.from(chef, food).serialize());
        } else if (request.equals("GET /honux/pasta")) {
            Chef chef = new Honux();
            Food food = chef.createPasta();
            socket.writeResponse(ResponseDto.from(chef, food).serialize());
        } else if (request.equals("GET /honux/steak")) {
            Chef chef = new Honux();
            Food food = chef.createSteak();
            socket.writeResponse(ResponseDto.from(chef, food).serialize());
        } else if (request.equals("GET /pyro/pizza")) {
            Chef chef = new Pyro();
            Food food = chef.createPizza();
            socket.writeResponse(ResponseDto.from(chef, food).serialize());
        } else if (request.equals("GET /pyro/pasta")) {
            Chef chef = new Pyro();
            Food food = chef.createPasta();
            socket.writeResponse(ResponseDto.from(chef, food).serialize());
        } else if (request.equals("GET /pyro/steak")) {
            Chef chef = new Pyro();
            Food food = chef.createSteak();
            socket.writeResponse(ResponseDto.from(chef, food).serialize());
        } else {
            socket.writeResponse("잘못된 요청입니다.");
        }

        socket.close();
    }
}
