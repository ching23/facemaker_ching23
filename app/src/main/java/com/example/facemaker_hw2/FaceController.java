/*
 *   @author: Caitlin Ching
 *   Face controller class - implements usages for buttons, seekbar, and spinner
 *   Tablet should run vertically
 *   CS301-A
 *   03/03/21
 */

package com.example.facemaker_hw2;

import android.app.Activity;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;

public class FaceController extends Activity implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    private com.example.facemaker_hw2.Face myFace;

    // face controller constructor
    public FaceController(com.example.facemaker_hw2.Face face){
        myFace = face;
    }

    // hairstyle changes depending on style selected from spinner
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        /**
         External Citation
         Date: 28 February 2021
         Problem: Didn't know how to use a spinner and switch between them

         Resource:
         https://developer.android.com/guide/topics/ui/controls/spinner
         Solution: I used the example code from this post.
         */

        switch (position) {
            case 0:
                myFace.hairStyle = 0;
                myFace.invalidate();
                break;
            case 1:
                myFace.hairStyle = 1;
                myFace.invalidate();
                break;
            case 2:
                myFace.hairStyle = 2;
                myFace.invalidate();
                break;
        }
    }

    // colors set to the progress on seekbar
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        /**
         External Citation
         Date: 28 February 2021
         Problem: Didn't know how to switch between multiple seekbars

         Resource:
         https://stackoverflow.com/questions/8719632/multiple-seekbars-listener
         Solution: I used the example code from this post.
         */

        switch (seekBar.getId())
        {
            case R.id.redSeekBar:
                myFace.redColor = progress;
                myFace.invalidate();
                break;
            case R.id.blueSeekBar:
                myFace.blueColor = progress;
                myFace.invalidate();
                break;
            case R.id.greenSeekBar:
                myFace.greenColor = progress;
                myFace.invalidate();
                break;
        }
    }


    //radio button and random button
    public void onClick(View view) {

        /**
         External Citation
         Date: 28 February 2021
         Problem: Didn't know how to switch from multiple buttons

         Resource:
         https://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android
         Solution: I used the example code from this post.
         */

        switch(view.getId()) {
            // creates a random face when random button is pushed
            case R.id.randomFace:
                // randomize face
                myFace.randomize();
                myFace.invalidate();
                break;

            case R.id.Hair:
                // changes hair if checked
                myFace.hairChecked = true;
                myFace.eyesChecked = false;
                myFace.skinChecked = false;

                // retrieves red, blue, and green values from previous hair color
                int red = Color.red(myFace.finalHairColor.getColor());
                int green = Color.green(myFace.finalHairColor.getColor());
                int blue = Color.blue(myFace.finalHairColor.getColor());

                /**
                 External Citation
                 Date: 28 February 2021
                 Problem: Didn't know how to call from main activity

                 Resource:
                 https://developer.android.com/training/basics/firstapp/starting-activity
                 Solution: I used the example code from this post
                 */

                //sets corresponding seek bar to r/g/b hair color value
                MainActivity.getInstance().redSeekBar.setProgress(red);
                MainActivity.getInstance().greenSeekBar.setProgress(green);
                MainActivity.getInstance().blueSeekBar.setProgress(blue);

                // redraw face
                myFace.invalidate();
                break;

            case R.id.Eyes:
               // changes eyes when checked
                myFace.hairChecked = false;
                myFace.eyesChecked = true;
                myFace.skinChecked = false;

                // retrieves red, blue, and green values from previous eye color
                int redEyes = Color.red(myFace.finalEyeColor.getColor());
                int greenEyes = Color.green(myFace.finalEyeColor.getColor());
                int blueEyes = Color.blue(myFace.finalEyeColor.getColor());

                MainActivity.getInstance().redSeekBar.setProgress(redEyes);
                MainActivity.getInstance().greenSeekBar.setProgress(greenEyes);
                MainActivity.getInstance().blueSeekBar.setProgress(blueEyes);

                // redraw face
                myFace.invalidate();
                break;

            case R.id.Skin:
                // changes skin when checked
                myFace.hairChecked = false;
                myFace.eyesChecked = false;
                myFace.skinChecked = true;

                // retrieves red, blue, and green values from previous skin color
                int redSkin = Color.red(myFace.finalSkinColor.getColor());
                int greenSkin = Color.green(myFace.finalSkinColor.getColor());
                int blueSkin = Color.blue(myFace.finalSkinColor.getColor());

                MainActivity.getInstance().redSeekBar.setProgress(redSkin);
                MainActivity.getInstance().greenSeekBar.setProgress(greenSkin);
                MainActivity.getInstance().blueSeekBar.setProgress(blueSkin);

                // redraw face
                myFace.invalidate();
                break;
        }

    }

    // empty functions
    public void onNothingSelected(AdapterView<?> adapterView) { }

    public void onStartTrackingTouch(SeekBar seekBar) { }

    public void onStopTrackingTouch(SeekBar seekBar) { }
}
