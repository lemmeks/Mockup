package com.research.lemmeks.mockup;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//imports for file and listview
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.research.lemmeks.mockup.TrackService.TrackBinder;

public class SelectSpeechActivity extends AppCompatActivity {

    public final static String SELECTED_TRACK = "com.research.lemmeks.mockup.TRACK_ID";

    private ArrayList<Track> trackList;
    private ListView trackView;

    private TrackService trackSrv;
    private Intent playIntent;
    private boolean trackBound=false;

    private String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_speech);
/*
        trackView = (ListView) findViewById(R.id.track_list);
        trackList = new ArrayList<Track>();

        getTrackList();

        Collections.sort(trackList, new Comparator<Track>() {
            @Override
            public int compare(Track o1, Track o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });

        TrackAdapter trackAdt = new TrackAdapter(this, trackList);
        trackView.setAdapter(trackAdt);*/

        final ArrayList<String> tracksList = GetFiles(Environment.getExternalStorageDirectory() + "/Music/");
        trackView = (ListView) findViewById(R.id.track_list);

        trackView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tracksList));

        trackView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                path = Environment.getExternalStorageDirectory() + "/Music/" + tracksList.get(position);
                trackPicked(view);
            }
        });
    }

    private ServiceConnection trackConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TrackBinder binder = (TrackBinder) service;
            //get service
            trackSrv = binder.getService();
            //pass list
            trackSrv.setList(trackList);
            trackBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            trackBound = false;
        }
    };
/*
    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent == null){
            playIntent = new Intent(this, TrackService.class);
            bindService(playIntent, trackConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }
*/
    public void getTrackList(){
        //retrieve track info
        /*ContentResolver trackResolver = getContentResolver();
        Uri trackUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor trackCursor = trackResolver.query(trackUri, null, null, null, null);

        if(trackCursor != null && trackCursor.moveToFirst()){
            //get column
            int titleColumn = trackCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = trackCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int diffColumn = trackCursor.getColumnIndex(MediaStore.Audio.Media.COMPOSER);
            //add tracks to list
            do{
                long thisId = trackCursor.getLong(idColumn);
                String thisTitle = trackCursor.getString(titleColumn);
                String thisDiff = trackCursor.getString(diffColumn);
                trackList.add(new Track(thisId, thisTitle, thisDiff));
            }
            while (trackCursor.moveToNext());
        }*/
    }

    public void trackPicked(View view) {

        //trackSrv.setTrack(Integer.parseInt(view.getTag().toString()));
        //String path = trackSrv.getTrack();

        //trackSrv.playTrack();

        //Toast.makeText(SelectSpeechActivity.this, "track pressed", Toast.LENGTH_LONG).show();
/*
        Intent playerIntent = new Intent(this, PlayerActivity.class);

        playerIntent.putExtra(SELECTED_TRACK, path);
        startActivity(playerIntent);
        Intent scaffoldingIntent = new Intent(this, ScaffoldingActivity.class);
        startActivity(scaffoldingIntent);*/

        Intent evalIntent = new Intent(this, EvaluationActivity.class);
        startActivity(evalIntent);
    }

    public ArrayList<String> GetFiles(String DirectoryPath) {
        ArrayList<String> MyFiles = new ArrayList<String>();
        File f = new File(DirectoryPath);

        f.mkdirs();
        File[] files = f.listFiles();
        if (files.length == 0)
            return null;
        else {
            for (int i=0; i<files.length; i++)
                MyFiles.add(files[i].getName());
        }

        return MyFiles;
    }
}
