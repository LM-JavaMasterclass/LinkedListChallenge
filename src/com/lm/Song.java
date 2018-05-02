package com.lm;

public class Song {
    private String songName;
    private int duration; // in seconds

    public Song(String songName, int duration) {
        this.songName = songName;
        this.duration = duration;
    }

    public String getSongName() {
        return songName;
    }


}
