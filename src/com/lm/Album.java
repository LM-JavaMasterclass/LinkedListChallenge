package com.lm;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String albumName;
    private String artistName;
    private ArrayList<Song> songs;

    public Album(String albumName, String artistName, ArrayList<Song> songs) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.songs = songs;
    }

    public Album(String albumName, String artistName) {
        this.albumName = albumName;
        this.artistName = artistName;
        this.songs = new ArrayList<Song>();
    }

    public String getAlbumName() {
        return albumName;
    }

    public boolean addToPlayList (int trackNumber, LinkedList<Song> playlist) { // From TB answer: to create directly in Main
        int index = trackNumber - 1;
        if ((index>=0) && (index<=this.songs.size())) {
            playlist.add(this.songs.get(index));
            return true;
        }
        System.out.println("This album does not have a track with number "+ trackNumber);
        return false;
    }

    public Song findSong(String name) {
        for (int i=0; i<this.songs.size(); i++) { // alternative for (Song checkedSong: this.songs
               // for each entry, it creates an item named "checkedSong" and goes through the list
            Song song = this.songs.get(i);
            if (song.getSongName().equals(name)){
                return song;
            }
        }
        return null;
    }
}
