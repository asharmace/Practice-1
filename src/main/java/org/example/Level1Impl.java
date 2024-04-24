package org.example;

import java.util.HashMap;
import java.util.Map;

public class Level1Impl implements Level1{

    Map<String, Map<String, Integer>> records = new HashMap<>();

    @Override
    public void addRecord(int timestamp, String record, String field, int value) {
        Map<String, Integer> fields = records.getOrDefault(record, new HashMap<String, Integer>());
        fields.put(field, value);
        records.put(record, fields);
    }

    @Override
    public Integer getValue(int timestamp, String record, String field) {
        if(records.containsKey(record)) {
            Map<String, Integer> fields = records.get(record);
            if(fields.containsKey(field)) {
                return fields.get(field);
            }
        }
        return null;
    }

    @Override
    public Integer updateValue(int timestamp, String record, String field, int value){
        if (records.containsKey(record)) {
            Map<String, Integer> fields = records.get(record);
            fields.put(field, value);
            records.put(record, fields);
            return value;
        }
        return null;
    }
}
