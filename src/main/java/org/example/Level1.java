package org.example;

public interface Level1 {

    default void addRecord(int timestamp, String record, String field, int value) {

    }

    default Integer getValue(int timestamp, String record, String field) {
        return null;
    }

    default Integer updateValue(int timestamp, String record, String field, int value) {
        return null;
    }
}
