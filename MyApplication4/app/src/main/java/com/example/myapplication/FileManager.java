package com.example.myapplication;

public class FileManager {
    private static FileManager instance;
    private FileManager() {
    }
    public static synchronized FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }
}
