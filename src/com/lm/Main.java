package com.lm;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Created by Laura Macia: May 1, 2018
        // Udemy - Complete Java Masterclass with Tim Buchalka
        // Challenge: LinkedList
        //
        // Create a program that implements a playlist for songs
        // Create a Song class having Title and Duration for a song.
        // The program will have an Album class containing a list of songs.
        // The albums will be stored in an ArrayList
        // Songs from different albums can be added to the playlist and will appear in the list in the order
        // they are added.
        // Once the songs have been added to the playlist, create a menu of options to:-
        // Quit,Skip forward to the next song, skip backwards to a previous song.  Replay the current song.
        // List the songs in the playlist
        // A song must exist in an album before it can be added to the playlist (so you can only play songs that
        // you own).
        // Hint:  To replay a song, consider what happened when we went back and forth from a city before we
        // started tracking the direction we were going.
        // As an optional extra, provide an option to remove the current song from the playlist
        // (hint: listiterator.remove()

        Song clandestino = new Song("Clandestino", 150) ;
        Song desaparecido = new Song("Desaparecido", (47+3*60)) ;
        Song bongoBong = new Song("Bongo Bong", 158) ;
        Song jeNeTaimePlus = new Song("Je ne t'aime plus", 122) ;
        Song merry_blues = new Song("Merry Blues", (36+3*60)) ;
        Song bixo = new Song("Bixo", (60+52)) ;

        ArrayList<Song> clandestinoSongs = new ArrayList<Song>();
        clandestinoSongs.add(clandestino);
        clandestinoSongs.add(desaparecido);
        clandestinoSongs.add(bongoBong);
        clandestinoSongs.add(jeNeTaimePlus);

        ArrayList<Song> esperanzaSongs = new ArrayList<Song>();
        esperanzaSongs.add(merry_blues);
        esperanzaSongs.add(bixo);

        Album albumClandestino = new Album("Clandestino", "Manu Chao", clandestinoSongs);
        Album albumEsperanza = new Album("Proxima Estacion: Esperanza", "Manu Chao", esperanzaSongs);

        ArrayList<Album> albums = new ArrayList<Album>();
        albums.add(albumClandestino);
        albums.add(albumEsperanza);

        Playlist myPlaylist = new Playlist("Laura's list", albums);

        albumEsperanza.addToPlayList(2,myPlaylist.getPlaylist());
        albumClandestino.addToPlayList(1,myPlaylist.getPlaylist());
        albumEsperanza.addToPlayList(1,myPlaylist.getPlaylist());
        albumClandestino.addToPlayList(1,myPlaylist.getPlaylist());

//        createPlaylist("Laura's list", albums);

        navigateSongs(myPlaylist);

    }


    private static void navigateSongs(Playlist playlist) {
        LinkedList<Song> songsList = playlist.getPlaylist();
        ListIterator<Song> listIterator = songsList.listIterator();
        boolean quit = false;
        boolean goingForward = true;

        if (songsList.isEmpty()) {
            System.out.println("Playlist has no songs.");
            return;
        } else {
            System.out.println("Currently playing " + listIterator.next().getSongName());
            printOptions();
        }

        while (!quit) {
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0:
                    System.out.println("No longer playing songs.");
                    quit = true;
                    break;
                case 1:
                    if (!goingForward){
                        if (listIterator.hasNext()) {
                            listIterator.next();
                            goingForward = true;
                        }
                    }
                    if (listIterator.hasNext()){
                        System.out.println("Now playing " + listIterator.next().getSongName());
                    } else {
                        System.out.println("No more songs in playlist. Moving to first song again.");
                        listIterator = songsList.listIterator();
                        System.out.println("Now playing " + listIterator.next().getSongName());
                    }
                    printOptions();
                    break;
                case 2:
                    if(goingForward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        goingForward = false;
                    }
                    if (listIterator.hasPrevious()){
                        System.out.println("Now playing " + listIterator.previous().getSongName());
                    } else {
                        System.out.println("Already at beginning of the list.");
                        System.out.println("Playing first song: " + listIterator.next().getSongName());
                        goingForward = true;
                    }
                    printOptions();
                    break;
                case 3:
                    if (goingForward) {
                        System.out.println("Replaying " + listIterator.previous().getSongName());
                        goingForward = false;
                    } else {
                        listIterator.next();
                        System.out.println("Replaying " + listIterator.previous().getSongName());
                    }
                    printOptions();
                    break;
                case 4:
                    listSongs(playlist);
                    break;
                case 5:
                    printOptions();
                    break;
            }
        }
    }

    public static void listSongs(Playlist playlist){
        LinkedList<Song> songsList = playlist.getPlaylist();
        Iterator<Song> iterator = songsList.iterator();
        int i = 1;
        System.out.println(playlist.getName() + " list of songs:");
        while (iterator.hasNext()) {
            System.out.println(i + ". " + iterator.next().getSongName());
            i++;
        }
    }




    private static void printOptions() {
        System.out.println("What do you want to do next?");
        System.out.println("0 - Quit playlist\n" +
                "1 - Skip forward to next song\n" +
                "2 - Skip backwards to previous song\n" +
                "3 - Replay current song\n" +
                "4 - List songs in the playlist\n" +
                "5 - Print options");
    }

    private static Playlist createPlaylist(String name, ArrayList<Album> albums) {
        Playlist playlist = new Playlist(name, albums);
        boolean quit = false;

        while (!quit) {
            if (!playlist.addSong()){
                System.out.println("Could not add song.");
            }
            quit = quitAdd();
        }
        return playlist;
    }

    private static boolean quitAdd() {
        int i = 0;
        System.out.println("Do you want to enter another song? (1) Yes, (2) No");
        i = scanner.nextInt();
        scanner.nextLine();

        while (!(i == 1 || i == 2)) {
            System.out.println("Please enter 1 or 2.");
            System.out.println("Do you want to enter another song? (1) Yes, (2) No");
            i = scanner.nextInt();
            scanner.nextLine();
        }
        switch (i) {
            case 1:
                return false;
            case 2:
                return true;
        }
        System.out.println("Something went wrong.");
        return true;
    }


}
