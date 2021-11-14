package com.picsart.redis.service;

import java.util.*;

public class ListService {
    private static final Map<String, List<String>> list = new HashMap<>();

    private static String addElement(String key, String value) {
        list.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        return "OK";
    }


    private static String getElement(String key, String onInclusive, String toExclusive) {
        List<String> result = list.get(key);
        int start;
        int end;
        try {
            start = Integer.parseInt(onInclusive);
            end = Integer.parseInt(toExclusive);
        } catch (Exception e) {
            return "Command is invalid";
        }
        if (result == null || result.isEmpty())
            return "NIL";
        else if (result.size() < end || start >= end) {
            return result.toString();
        } else {
            return result.subList(start, end).toString();
        }
    }


    private static String deleteElement(String key) {
        List<String> remove = list.remove(key);
        if (remove == null)
            return "NIL";
        else
            return remove.toString();
    }

    public static String doCommand(String command) {
        String[] commandElements = command.split("\\s+");
        if (commandElements[0].equals("LIST_PUSH") && commandElements.length == 3) {
            return addElement(commandElements[1], commandElements[2]);
        } else if (commandElements[0].equals("LIST_RANGE") && commandElements.length == 4) {
            return getElement(commandElements[1], commandElements[2], commandElements[3]);
        } else if (commandElements[0].equals("LIST_POP") && commandElements.length == 2) {
            return deleteElement(commandElements[1]);
        } else
            return "Command is invalid";

    }
}
