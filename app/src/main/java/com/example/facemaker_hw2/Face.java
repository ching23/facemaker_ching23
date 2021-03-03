/*
 *   @author: Caitlin Ching
 *   Face class - draws facial features like head, eyes, mouth, and nose
 *   Tablet should run vertically
 *   CS301-A
 *   03/03/21
 */
package com.example.facemaker_hw2;

import android.graphics.Canvas;
import java.util.Random;
import android.content.Context;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.SurfaceView;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class Face extends SurfaceView {

    // instance variables to change colors
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    protected int hairStyle;

    // radio buttons set to false unless user clicks on it
    public boolean hairChecked = false;
    public boolean eyesChecked = false;
    public boolean skinChecked = false;

    // custom red, blue, and green colors
    public int redColor;
    public int blueColor;
    public int greenColor;

    // paints for face
    Random random = new Random();
    Paint finalSkinColor = new Paint();
    Paint finalHairColor = new Paint();
    Paint eyeOutline = new Paint();
    Paint eyeWhite = new Paint();
    Paint finalEyeColor = new Paint();
    Paint nosePaint = new Paint();
    Paint mouthPaint = new Paint();

    // set value of radius
    public float radius = 100;

    public Face (Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        // initial face is randomly drawn
        this.randomize();

        // initialize set colors for facial features
        this.eyeOutline.setColor(Color.BLACK);
        this.eyeWhite.setColor(Color.WHITE);
        this.nosePaint.setColor(0XFF583232);
        this.mouthPaint.setColor(0XFFFE8585);
    }

    // random colors for skin, eyes, hair, and hairstyle
    public void randomize() {

        /**
         External Citation
         Date: 01 March 2021
         Problem: Didn't know how to generate a random color on click

         Resource:
         https://stackoverflow.com/questions/5280367/android-generate-random-color-on-click
         Solution: I used the example code from this post
         */

        // set skin, eyes, hair colors as random
        skinColor = Color.argb(255, random.nextInt(256),
                random.nextInt(256), random.nextInt(256));
        eyeColor = Color.argb(255, random.nextInt(256),
                random.nextInt(256), random.nextInt(256));
        hairColor = Color.argb(255, random.nextInt(256),
                random.nextInt(256), random.nextInt(256));
        hairStyle = random.nextInt(3);

        hairChecked = false;
        eyesChecked = false;
        skinChecked = false;
    }

    // draw facial features
    public void onDraw(Canvas canvas) {
        drawHead(canvas);
        drawEyes(canvas);
        drawHair(canvas);
        drawNose(canvas);
        drawMouth(canvas);
    }

    // draw basic head and sets skin color
    public void drawHead(Canvas canvas) {

        float faceX = getWidth();
        float faceY = getHeight();

        if (faceX > faceY) {
            radius = faceY / 4;
        } else {
            radius = faceX / 4;
        }

        // draw skin if skin is checked
        if(skinChecked == true) {
            skinColor = (Color.rgb(redColor, greenColor, blueColor));
        }

        // sets current skin color to final skin color
        this.finalSkinColor.setColor(skinColor);

        /**
         External Citation
         Date: 01 March 2021
         Problem: Didn't know how to draw a rounded rectangle for the face

         Resource:
         https://stackoverflow.com/questions/21934531/draw-rounded-rectangle-using-rectf-and-canvas/21934676
         Solution: I used the example code from this post
         */

        // draw face shape
        RectF head = new RectF(faceX/6, faceY/8,
                faceX - faceX/6, faceY - faceY/8);
        canvas.drawOval(head, finalSkinColor);
    }

    // draws eyes and sets color from seekbar
    public void drawEyes(Canvas canvas) {
        float eyeRadius = 150;
        float innerEyeRadius = 110;
        float pupilRadius = 70;

        // draw eyes if eyes are checked
        if(eyesChecked == true) {
            eyeColor = (Color.rgb(redColor, greenColor, blueColor));
        }

        // sets current eye color to final eye color
        this.finalEyeColor.setColor(eyeColor);

        float eyeX = getWidth();
        float eyeY = getHeight();

        eyeOutline.setStrokeWidth(15); // width of the eye outline
        eyeOutline.setStyle(Paint.Style.STROKE);

        // eye outline
        canvas.drawCircle(eyeX / 2 - 200, eyeY / 4 + 200, eyeRadius, eyeOutline);
        canvas.drawCircle(eyeX / 2 + 200,eyeY / 4 + 200, eyeRadius, eyeOutline);

        // eye whites
        canvas.drawCircle(eyeX / 2 - 200,eyeY / 4 + 200, eyeRadius, eyeWhite);
        canvas.drawCircle(eyeX / 2 + 200,eyeY / 4 + 200, eyeRadius, eyeWhite);

        // iris
        canvas.drawCircle(eyeX / 2 - 200,eyeY / 4 + 200, innerEyeRadius, finalEyeColor);
        canvas.drawCircle(eyeX / 2 + 200,eyeY / 4 + 200, innerEyeRadius, finalEyeColor);

        // pupil
        eyeOutline.setStyle(Paint.Style.FILL);
        canvas.drawCircle(eyeX / 2 - 200,eyeY / 4 + 200, pupilRadius, eyeOutline);
        canvas.drawCircle(eyeX / 2 + 200,eyeY / 4 + 200, pupilRadius, eyeOutline);
    }

    // draws hair picked from spinner
    public void drawHair(Canvas canvas) {

        // draw hair if hair is checked
        if(hairChecked == true) {
            hairColor = (Color.rgb(redColor, greenColor, blueColor));
        }

        // sets current hair color to final hair color
        this.finalHairColor.setColor(hairColor);

        float hairX = getWidth();
        float hairY = getHeight();
        float hairRadius = 125;

        switch(hairStyle){
            // bald head
            case 0:
                break;

            // braided hair
            case 1:
                // around the top
                canvas.drawCircle(2 * (hairX / 8),hairY / 4 - 30, hairRadius, finalHairColor);
                canvas.drawCircle(3 * (hairX / 8),hairY / 5 - 40, hairRadius, finalHairColor);
                canvas.drawCircle(4 * (hairX / 8),hairY / 6 - 50, hairRadius, finalHairColor);
                canvas.drawCircle(5 * (hairX / 8),hairY / 5 - 40, hairRadius, finalHairColor);
                canvas.drawCircle(6 * (hairX / 8),hairY / 4 - 30, hairRadius, finalHairColor);

                // longer braids - right side
                canvas.drawCircle((hairX / 8) + 45,hairY/3 - 20, hairRadius, finalHairColor);
                canvas.drawCircle((hairX / 8) + 45,hairY/3 + 220, hairRadius, finalHairColor);
                canvas.drawCircle((hairX / 8) + 45,hairY/3 + 460, hairRadius, finalHairColor);
                canvas.drawCircle((hairX / 8) + 45,hairY/3 + 700, hairRadius, finalHairColor);
                // longer braids - left side
                canvas.drawCircle(7 * (hairX / 8) - 45,hairY / 3 - 20, hairRadius, finalHairColor);
                canvas.drawCircle(7 * (hairX / 8) - 45,hairY / 3 + 220, hairRadius, finalHairColor);
                canvas.drawCircle(7 * (hairX / 8) - 45,hairY / 3 + 460, hairRadius, finalHairColor);
                canvas.drawCircle(7 * (hairX / 8) - 45,hairY / 3 + 700, hairRadius, finalHairColor);
                break;

            /**
             External Citation
             Date: 01 March 2021
             Problem: Didn't know how to draw a triangle

             Resource:
             https://stackoverflow.com/questions/21934531/draw-rounded-rectangle-using-rectf-and-canvas/21934676
             Solution: I used the example code from this post
             */

            // party hat
            case 2:
                Path path = new Path();
                path.moveTo(hairX / 6 + 220, hairY / 6);
                path.lineTo(hairX / 6 + 430, hairY / 6 - 330);
                path.lineTo(hairX / 6 + 640, hairY / 6);
                path.lineTo(hairX / 6 - 500, hairY / 6);
                canvas.drawPath(path, finalHairColor);
        }
    }


    // draws circular nose
    public void drawNose(Canvas canvas) {
        float noseX = getWidth();
        float noseY = getHeight();
        float noseRadius = 75;

        if (noseX > noseY) {
            radius = noseY / 2;
        } else {
            radius = noseX / 2;
        }

        canvas.drawCircle(noseX / 2 + 10, noseY / 2 + 100, noseRadius, nosePaint);
    }

    // draws smirking mouth
    public void drawMouth(Canvas canvas) {

        /**
         External Citation
         Date: 01 March 2021
         Problem: Didn't know how to draw a half circle

         Resource:
         https://stackoverflow.com/questions/31705870/how-to-draw-a-half-circle-in-android
         Solution: I used the example code from this post
         */

        float mouthX = getWidth();
        float mouthY = getHeight();

        if (mouthX > mouthY) {
            radius = mouthY / 4;
        } else {
            radius = mouthX / 4;
        }

        Path path = new Path();
        path.addCircle(mouthX / 3, mouthY / 3, radius, Path.Direction.CW);

        float cx, cy;
        final RectF mouth = new RectF();

        cx = mouthX / 2;
        cy = mouthY / 2 + mouthY / 13;

        mouth.set(cx - radius, cy - radius,
                cx + radius, cy + radius);
        canvas.drawArc(mouth, 30, 120, false, mouthPaint);
    }
}
