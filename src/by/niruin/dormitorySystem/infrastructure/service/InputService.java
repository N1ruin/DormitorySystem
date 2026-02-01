package by.niruin.dormitorySystem.infrastructure.service;

import by.niruin.dormitorySystem.infrastructure.annotation.Component;

import java.util.Scanner;
@Component
public class InputService {
    private final Scanner scanner = new Scanner(System.in);

    public String inputLine() {
        return scanner.nextLine();
    }
}
