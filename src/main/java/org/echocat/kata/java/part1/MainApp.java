package org.echocat.kata.java.part1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("UseOfSystemOutOrSystemErr")
@SpringBootApplication
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
        System.out.println(getHelloWorldText());
    }

    public static String getHelloWorldText() {
        return "Hello world!";
    }

}
