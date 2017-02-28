package com.research.lemmeks.mockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.os.IBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.MenuItem;
import android.view.View;

import static com.research.lemmeks.mockup.MainActivity.SELECTED_USER;


public class UserMainActivity extends AppCompatActivity {

    private TrackService trackSrv;
    private Intent playIntent;
    private boolean trackBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        Intent intent = getIntent();
        int uid = intent.getIntExtra(SELECTED_USER, 0);

        TextView textView = (TextView) findViewById(R.id.uidTextView);
        textView.setText("User #" + uid);
    }

    public void selectSpeech(View view){
        Intent selSpeechIntent = new Intent(this, SelectSpeechActivity.class);
        startActivity(selSpeechIntent);
    }
}
