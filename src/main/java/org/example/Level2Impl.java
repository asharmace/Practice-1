package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level2Impl implements Level2 {

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

    @Override
    public String[] scan(int timestamp, String record) {
        return scanByPrefix(timestamp, record, null);
    }

    @Override
    public String[] scanByPrefix(int timestamp, String record, String prefix) {
        if (!records.containsKey(record)) {
            return null;
        }
        Map<String, Integer> fields = records.get(record);
        List<String> result = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : fields.entrySet()) {
            if(prefix == null || entry.getKey().startsWith(prefix)) {
                result.add(entry.getKey() + "=" + entry.getValue());
            }
        }

        return result.toArray(new String[0]);
    }
}
