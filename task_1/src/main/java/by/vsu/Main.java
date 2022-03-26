package by.vsu;

import by.vsu.models.Knight;

import java.util.Scanner;


public class Main {

    static public void main(String[] arg) {

        Knight knight = new Knight();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(knight, scanner);
        menu.start();
    }
}
