package by.niruin.dormitorySystem.infrastructure.service;

import java.util.Scanner;

public class InputService {
    private final Scanner scanner = new Scanner(System.in);

    public String inputLine() {
        return scanner.nextLine();
    }
}
