package com.research.lemmeks.mockup;

/**
 * Created by LemmeKS on 3/2/2017.
 */

public class Track {
    private long id;
    private String title;
    private String difficulty;

    public Track(long trackID, String trackTitle, String trackDifficulty){
        id = trackID;
        title = trackTitle;
        difficulty = trackDifficulty;
    }

    public long getId() {return  id;}

    public String getTitle() {return title;}

    public String getDifficulty() {return difficulty;}
}
