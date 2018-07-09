package com.example.android.musicgrid;

public class sequenceObject {
    String name;
    private int audio;
    int volume;
    int pan;
    int looptime;

    public sequenceObject(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }
}
