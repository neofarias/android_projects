package com.example.marciosantos.animationstransitions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Slide slide = new Slide(Gravity.BOTTOM);
        getWindow().setEnterTransition(slide);
    }
}
