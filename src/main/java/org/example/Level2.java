package org.example;

public interface Level2 {

    default void addRecord(int timestamp, String record, String field, int value) {

    }

    default Integer getValue(int timestamp, String record, String field) {
        return null;
    }

    default Integer updateValue(int timestamp, String record, String field, int value) {
        return null;
    }

    default String[] scan(int timestamp, String record) {
        return null;
    }

    default String[] scanByPrefix(int timestamp, String record, String prefix) {
        return null;
    }
}
