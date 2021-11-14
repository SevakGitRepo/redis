package com.picsart.redis.service;

import java.util.*;

public class StringService {
    private static final Map<String, String> string = new HashMap<>();

    private static String addElement(String key, String value){
            string.put(key, value);
        return "OK";
    }


    private static String getElement(String key){
        return string.getOrDefault(key, "NIL");
    }


    private static String deleteElement(String key){
        return Objects.requireNonNullElse(string.remove(key), "WRONG KEY");
    }

    public static String doCommand(String command){
        String[] commandElements = command.split("\\s+");
        if(commandElements[0].equals("SET") && commandElements.length==3) {
            return addElement(commandElements[1], commandElements[2]);
        }
        else if(commandElements[0].equals("GET") && commandElements.length==2) {
            return getElement(commandElements[1]);
        }
        else if(commandElements[0].equals("DELETE") && commandElements.length==2) {
            return deleteElement(commandElements[1]);
        }
        else
           return "Command is invalid";

    }
}
