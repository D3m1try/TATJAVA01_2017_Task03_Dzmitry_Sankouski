package com.epam.news_manager.view;

import com.epam.news_manager.controller.impl.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Dzmitry_Sankouski on 31-Jan-17.
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = "";
        Controller controller = new Controller();

        while (true) {
            try {
                userInput = userInputReader.readLine();
            } catch (IOException e) {
                System.out.println("IO Exception while reading keyboard input. Program will be terminated");
                return;
            }
            System.out.println(controller.executeTask(userInput));
        }
    }
}
