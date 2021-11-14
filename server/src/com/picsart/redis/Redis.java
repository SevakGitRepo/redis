package com.picsart.redis;

import com.picsart.redis.service.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Redis {

    private static final Set<String> stringSet = Set.of("SET", "GET", "DELETE");
    private static final Set<String> listSet = Set.of("LIST_PUSH", "LIST_RANGE", "LIST_POP");
    private static final Set<String> setSet = Set.of("SET_ADD", "SET_GET", "SET_DELETE");
    private static final Set<String> sortedSetSet = Set.of("SORT_SET_ADD", "SORT_SET_GET", "SORT_SET_DELETE");
    private static final Set<String> hashesSet = Set.of("HASH_SET_ADD", "HASH_SET_GET", "HASH_SET_DELETE");

    private Map<String, String> string;
    private Map<String, List<String>> list;
    private List<Map<String, Set<String>>> set;
    private List<Map<String, Set<String>>> sortedSet;
    private List<Map<String, Map<String, String>>> hashes;


    public static String action(String command) {
        String commandStartWith = command.split(" ")[0];
        //Check Typeof operation
        //simple
        if (stringSet.contains(commandStartWith)){
            return StringService.doCommand(command);
        }
        //list
        else if(listSet.contains(commandStartWith)){
            return ListService.doCommand(command);
        }
        //set
        else if(setSet.contains(commandStartWith)){
            return SetService.doCommand(command);
        }
        //sorted set
        else if(sortedSetSet.contains(commandStartWith)){
            return SortedSetService.doCommand(command);
        }
        //hashes set
        else if(hashesSet.contains(commandStartWith)){
            return HashesService.doCommand(command);
        }
        //default
        else {
            return "Command is invalid";
        }
    }
}
