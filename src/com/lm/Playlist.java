package com.lm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Playlist {
    private Scanner scanner = new Scanner(System.in);

    private String name;
    private ArrayList<Album> albums;
    private LinkedList<Song> playlist;

    public Playlist(String name, ArrayList<Album> albums) {
        this.name = name;
        this.albums = albums;
        this.playlist = new LinkedList<Song>();
    }

    public LinkedList<Song> getPlaylist() {
        return playlist;
    }

    public String getName() {
        return name;
    }

    public boolean addSong() {
        String albumName;
        String songName;
        Album album;
        Song newSong;

        System.out.print("Enter album name of next song to add: ");
        albumName = scanner.nextLine();
        album = findAlbum(albumName);
        if (album != null) {
            System.out.print("Enter name of song to add from album '" + albumName + "': ");
            songName = scanner.nextLine();
            newSong = album.findSong(songName);
            if (newSong != null) {
                this.playlist.add(newSong);
                return true;
            } else {
                System.out.println("Song " + songName + " not found in album " + albumName);
                return false;
            }
        } else{
            System.out.println("Album " + albumName + " not found.");
            return false;
        }
    }

    private Album findAlbum(String name) {
        for (int i=0; i<this.albums.size(); i++) {
            Album album = this.albums.get(i);
            if (album.getAlbumName().equals(name)){
                return album;
            }
        }
        return null;
    }


}


