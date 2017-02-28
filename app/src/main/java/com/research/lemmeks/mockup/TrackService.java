package com.research.lemmeks.mockup;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.MediaController;
import java.util.ArrayList;
import android.content.ContentUris;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by LemmeKS on 5/2/2017.
 */

public class TrackService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener{
    @Nullable

    //media player
    private MediaPlayer player;
    //song list
    private ArrayList<Track> tracks;
    //current position
    private int trackPosn;

    private final IBinder trackBind = new TrackBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return trackBind;
    }

    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
        return false;
    }

    public void onCreate(){
        super.onCreate();
        trackPosn=0;
        player = new MediaPlayer();

        initTrackPlayer();
    }

    public void initTrackPlayer(){
        //set player properties
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }

    public void setList(ArrayList<Track> theTracks){
        tracks = theTracks;
    }
    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //start playback
        mp.start();
    }

    public void setTrack(int trackIndex){
        trackPosn=trackIndex;
    }

    public class TrackBinder extends Binder{
        TrackService getService() {
            return TrackService.this;
        }
    }

    public void playTrack() {
        //play track
        player.reset();

        Track playTrack = tracks.get(trackPosn);
        long currTrack = playTrack.getId();
        Uri trackUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, currTrack);

        try{
            player.setDataSource(getApplicationContext(), trackUri);
        }
        catch(Exception e){
            Log.e("TRACK SERVICE", "Error setting data source", e);
        }

        player.prepareAsync();
    }

    public String getTrack() {
        Track playTrack = tracks.get(trackPosn);
        Uri trackUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, playTrack.getId());
        //Toast.makeText(TrackService.this, "URI path is: "+ trackUri.getPath(), Toast.LENGTH_LONG).show();
        return trackUri.getPath();
    }
}
