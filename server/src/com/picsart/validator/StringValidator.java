package com.picsart.validator;

import java.util.HashSet;
import java.util.Set;

public class StringValidator {
    public static boolean isValid(String request){
        Set<String> setRequest = new HashSet<>();
        setRequest.add("SET");
        setRequest.add("GET");
        setRequest.add("DELETE");

        setRequest.add("LIST_PUSH");
        setRequest.add("LIST_RANGE");
        setRequest.add("LIST_POP");

        setRequest.add("SET_ADD");
        setRequest.add("SET_GET");
        setRequest.add("SET_DELETE");

        setRequest.add("SORT_SET_ADD");
        setRequest.add("SORT_SET_GET");
        setRequest.add("SORT_SET_DELETE");

        setRequest.add("HASH_SET_ADD");
        setRequest.add("HASH_SET_GET");
        setRequest.add("HASH_SET_DELETE");

        return setRequest.contains(request.split(" ")[0]);
    }
}
