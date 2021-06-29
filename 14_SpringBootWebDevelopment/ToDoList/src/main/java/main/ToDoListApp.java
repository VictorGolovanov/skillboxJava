package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListApp {
    // create database before you run app on new computer or new OS !!!
    public static void main(String[] args) {
        SpringApplication.run(ToDoListApp.class, args);
    }
}
