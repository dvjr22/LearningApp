package com.example.valdeslab.learningapp;

import java.util.UUID;

public class Data {

    private static String FIRST = "NO FIRST NAME ASSIGNED";
    private static String LAST = "NO LAST NAME ASSIGNED";
    private static int AGE = 0;

    private UUID id;
    private String first;
    private String last;
    private int age;

    public Data(){
        first = FIRST;
        last = LAST;
        age = AGE;
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
