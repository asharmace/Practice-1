package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level3Impl implements Level3 {

    Map<String, Map<String, Integer>> records = new HashMap<>();
    Map<String, Integer> ttlMap = new HashMap<>();

    @Override
    public void addRecord(int timestamp, String record, String field, int value, int ttl) {
        Map<String, Integer> fields = records.getOrDefault(record, new HashMap<String, Integer>());
        fields.put(field, value);
        records.put(record, fields);

        setTTLForField(record, field, timestamp+ ttl);
    }

    private void setTTLForField(String record, String field, int ttl) {
        ttlMap.put(getTTLKey(record, field), ttl);
    }

    private static String getTTLKey(String record, String field) {
        return record + "_" + field;
    }

    @Override
    public Integer getValue(int timestamp, String record, String field) {
        if(records.containsKey(record)) {
            Map<String, Integer> fields = records.get(record);
            if(fields.containsKey(field) && isAlive(timestamp, record, field)) {
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
            setTTLForField(record, field, timestamp+ value);
            return value;
        }
        return null;
    }

    @Override
    public String[] scan(int timestamp, String record) {
        return scanByPrefix(timestamp, record, "");
    }

    @Override
    public String[] scanByPrefix(int timestamp, String record, String prefix) {
        List<String> result = new ArrayList<>();

        if (records.containsKey(record)) {
            Map<String, Integer> fields = records.get(record);

            for (Map.Entry<String, Integer> entry : fields.entrySet()) {
                if (entry.getKey().startsWith(prefix)
                        && isAlive(timestamp, record, entry.getKey())) {
                    result.add(entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        return result.toArray(new String[0]);
    }

    private boolean isAlive(int timestamp, String record, String field) {
        String key = getTTLKey(record, field);

        if(ttlMap.containsKey(key) ) {
            return timestamp < ttlMap.get(key);
        }
        return true;
    }
}
