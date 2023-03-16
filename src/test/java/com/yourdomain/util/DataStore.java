package com.yourdomain.util;

import java.util.HashMap;

public class DataStore {

    private HashMap<String, Object> dataStore = new HashMap<>();

    public void put(String key, String value) {
        dataStore.put(key, value);
    }

    public Object get(String key){
        return dataStore.get(key);
    }

}
