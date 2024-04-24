package org.example;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Level1Tests {

    @Test
    public void stringPrefix() {
        String s = "abc";
        Assert.assertTrue(s.startsWith(""));
    }
    @Test
    public void testLevel1addAndGet() {

        int t = (int)System.currentTimeMillis()/1000;
        Level1 l1 = new Level1Impl();

        l1.addRecord(t+1, "record1", "field1", t+1);
        l1.addRecord(t+2, "record1", "field2", t+2);
        l1.addRecord(t+3, "record2", "field1", t+3);
        l1.addRecord(t+4, "record2", "field2", t+4);

        Assert.assertEquals(l1.getValue(t+5, "record1", "field1"), t+1);

    }

    @Test
    public void testLevel1Method2() {

        // Create a mock/stub of the Level1 interface
        Level1 level1 = Mockito.mock(Level1.class);

        // Set up test data
        int number = 5;

//        // Call the method on the mock
//        level1.method2(number);
//
//        // Assert that the method was called
//        Mockito.verify(level1).method2(number);

    }
}
