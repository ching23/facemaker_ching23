/*
 *   @author: Caitlin Ching
 *   Main activity class - implements all listeners from face controller
 *     and id's from activity main
 *   Program should run horizontally
 *   CS301-A
 *   03/03/21
 */

package com.example.facemaker_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    // get seekbars from activity main
    public SeekBar redSeekBar;
    public SeekBar blueSeekBar;
    public SeekBar greenSeekBar;

    private static MainActivity instance;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize class variables
        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        FaceController faceController = new FaceController((Face) surfaceView);

        // On click listeners for radio buttons
        RadioButton hairButton = findViewById(R.id.Hair);
        hairButton.setOnClickListener(faceController);
        RadioButton eyesButton = findViewById(R.id.Eyes);
        eyesButton.setOnClickListener(faceController);
        RadioButton skinButton = findViewById(R.id.Skin);
        skinButton.setOnClickListener(faceController);

        /**
         External Citation
         Date: 27 February 2021
         Problem: Didn't not know how to implement spinner widget

         Resource:
         https://developer.android.com/guide/topics/ui/controls/spinner#java
         Solution: I followed the example code and changed the code to fit the string array
         */

        // On item listener for spinner
        Spinner hairSpinner = findViewById(R.id.hairStyles);
        hairSpinner.setOnItemSelectedListener(faceController);

        // string for spinner and implements the names of each hairstyle
        String[] hairItems = new String[]{"Bald", "Braids" , "Party Hat"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hairItems);
        hairSpinner.setAdapter(adapter);

        // Listener for seekbar
        redSeekBar = findViewById(R.id.redSeekBar);
        redSeekBar.setOnSeekBarChangeListener(faceController);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        blueSeekBar.setOnSeekBarChangeListener(faceController);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        greenSeekBar.setOnSeekBarChangeListener(faceController);

        // On click listener for button
        Button randomFaceButton = findViewById(R.id.randomFace);
        randomFaceButton.setOnClickListener(faceController);

        instance = this;
    }

    /**
     External Citation
     Date: 27 February 2021
     Problem: Had problems returning instance

     Resource:
     https://stackoverflow.com/questions/35765077/correct-way-to-get-the-instance-of-application-in-android
     Solution: I used the example code from this post
     */

    public static MainActivity getInstance() {
        return instance;
    }
}
