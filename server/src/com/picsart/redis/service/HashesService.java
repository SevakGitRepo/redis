package com.picsart.redis.service;

import java.util.*;

public class HashesService {
    private static final Map<String, Map<String, String>> hashes = new HashMap<>();

    private static String addElement(String nameHashes, String key, String value){
        hashes.computeIfAbsent(nameHashes, name->new HashMap<>()).put(key, value);
        return "OK";
    }


    private static String getElement(String nameOfSet){
        Map<String, String> result = hashes.get(nameOfSet);
        if(result == null || result.isEmpty())
            return "NIL";
        else  {
            return result.toString();
        }
    }


    private static String deleteElement(String nameOfSet){
        Map<String, String> result = hashes.remove(nameOfSet);
        if(result == null)
            return "NIL";
        else
            return result.toString();
    }

    public static String doCommand(String command){
        String[] commandElements = command.split("\\s+");
        if(commandElements[0].equals("HASH_SET_ADD") && commandElements.length==4) {
            return addElement(commandElements[1], commandElements[2], commandElements[3]);
        }
        else if(commandElements[0].equals("HASH_SET_GET") && commandElements.length==2) {
            return getElement(commandElements[1]);
        }
        else if(commandElements[0].equals("HASH_SET_DELETE") && commandElements.length==2) {
            return deleteElement(commandElements[1]);
        }
        else
            return "Command is invalid";

    }
}
