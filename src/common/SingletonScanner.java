package common;

import java.util.Scanner;

public class SingletonScanner {
    private static final Scanner instance = new Scanner(System.in);

    private SingletonScanner(){
    }

    public static Scanner getBoard(){
        return instance;
    }
}
