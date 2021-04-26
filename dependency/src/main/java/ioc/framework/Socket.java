package ioc.framework;

import java.util.Scanner;

public class Socket {
    private final Scanner scanner;

    private Socket() {
        scanner = new Scanner(System.in);
    }

    public static Socket connect() {
        return new Socket();
    }

    public String readRequest() {
        System.out.print("$$$$ Request 뭐 드시고 싶으세요?: ");
        return scanner.nextLine();
    }

    public void writeResponse(String response) {
        System.out.println(String.format("#### Response: %s", response));
        System.out.println();
    }

    public void close() {
        // scanner.close();
    }
}
