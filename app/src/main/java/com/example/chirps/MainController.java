package com.example.chirps;

public class MainController {
    MultiController mc = new MultiController();

    public void requestFiles(){
        mc.gatherReminders();
    }
}