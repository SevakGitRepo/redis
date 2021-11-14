package com.picsart.redis.service;

import java.util.*;

public class SetService {
    private static final Map<String, Set<String>> set = new HashMap<>();

    private static String addElement(String key, String value){
        set.computeIfAbsent(key, k->new HashSet<>()).add(value);
        return "OK";
    }


    private static String getElement(String nameOfSet){
        Set<String> result = set.get(nameOfSet);
        if(result == null || result.isEmpty())
            return "NIL";
        else  {
            return result.toString();
        }
    }


    private static String deleteElement(String nameOfSet){
        Set<String> result = set.remove(nameOfSet);
        if(result == null)
            return "NIL";
        else
            return result.toString();
    }

    public static String doCommand(String command){
        String[] commandElements = command.split("\\s+");
        if(commandElements[0].equals("SET_ADD") && commandElements.length==3) {
            return addElement(commandElements[1], commandElements[2]);
        }
        else if(commandElements[0].equals("SET_GET") && commandElements.length==2) {
            return getElement(commandElements[1]);
        }
        else if(commandElements[0].equals("SET_DELETE") && commandElements.length==2) {
            return deleteElement(commandElements[1]);
        }
        else
            return "Command is invalid";

    }
}
