package com.picsart;

import com.picsart.thread.SendRequest;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new SendRequest().start();
    }
}
