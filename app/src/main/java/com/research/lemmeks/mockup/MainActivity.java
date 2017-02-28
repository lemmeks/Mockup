package com.research.lemmeks.mockup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public final static String SELECTED_USER = "com.research.lemmeks.mockup.USER_ID";
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
    }

    public void selectUser(View view){
        Intent intent = new Intent(this, UserMainActivity.class);
        int uid = 9;

        if(view.findViewById(R.id.btn_sbj_0) == (Button) findViewById(R.id.btn_sbj_0))
            uid = 0;
        else if(view.findViewById(R.id.btn_sbj_1) == (Button) findViewById(R.id.btn_sbj_1))
            uid = 1;
        else if(view.findViewById(R.id.btn_sbj_2) == (Button) findViewById(R.id.btn_sbj_2))
            uid = 2;
        else if(view.findViewById(R.id.btn_sbj_3) == (Button) findViewById(R.id.btn_sbj_3))
            uid = 3;
        else if(view.findViewById(R.id.btn_sbj_4) == (Button) findViewById(R.id.btn_sbj_4))
            uid = 4;

        intent.putExtra(SELECTED_USER, uid);
        startActivity(intent);
    }
}
