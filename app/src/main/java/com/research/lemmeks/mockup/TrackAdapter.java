package com.research.lemmeks.mockup;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by LemmeKS on 3/2/2017.
 */

public class TrackAdapter extends BaseAdapter {

    private ArrayList<Track> tracks;
    private LayoutInflater trackInf;

    public TrackAdapter(Context c, ArrayList<Track> theTracks){
        tracks=theTracks;
        trackInf=LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout trackLay = (LinearLayout)trackInf.inflate(R.layout.track, parent, false);
        TextView trackView = (TextView)trackLay.findViewById(R.id.track_title);
        TextView diffView = (TextView)trackLay.findViewById(R.id.track_diff);

        Track currTrack = tracks.get(position);

        trackView.setText(currTrack.getTitle());
        diffView.setText(("Level: " + currTrack.getDifficulty() + " / 5"));

        trackLay.setTag(position);
        return trackLay;
    }
}
