package com.example.rng;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TrailMakingUtilities extends AppCompatActivity {

    private int GREEN = Color.GREEN;
    private int RED = Color.RED;


    public void drawLine(ImageView imageView, Bitmap bitmap, double xStart, double yStart, double xEnd, double yEnd, int colour) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(colour);
        paint.setStrokeWidth(5);
        canvas.drawLine((float) xStart, (float) yStart, (float) xEnd, (float) yEnd, paint);
        imageView.setImageBitmap(bitmap);
    }

    public Bitmap undrawLine(Activity a, ArrayList<Integer> userPath, ArrayList<Integer> listImgView, int height, int width) {

        int lenOfUserPath = userPath.size();
        int node = 0;

        // overwrite current bitmap
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(5);

        double[] coordStartPoint;
        double[] coordEndPoint;
        int incorrectSequence = 0;

        for (int count = 0; count < userPath.size(); count ++) {
            if (listImgView.get(userPath.get(count)) != listImgView.get(count)) {
                node = count - 1;
                break;
            }
        }

        // remove values until the node that was clicked
        if (userPath.size() >= 1 && (listImgView.get(userPath.get(0)) != listImgView.get(0)) )
        {
            userPath.subList(0, userPath.size()).clear();
        }

        for (int i = 0; i < userPath.size(); i++) {
            if (userPath.get(i) == node) {
                for (int n = i+1; n < lenOfUserPath; n++) {
                    userPath.remove(userPath.size() - 1);
                }
                break;
            }
        }

        // if node was the starting node, remove it as well, so that user can click another starting node value
        if (userPath.size() == 1) {
            userPath.remove(0);
        }

        // redraw all the lines based on the current userPath
        for (int m = 0; m < userPath.size(); m++) {
            // check if the sequence is correct
            for (int j = 0; j < m+1; j++) {
                if (!listImgView.get(j).equals(listImgView.get(userPath.get(j)))) {
                    incorrectSequence = 1;
                    break;
                }
                incorrectSequence = 0;
            }

            // to draw the lines between the nodes
            if (m > 0) {

                coordStartPoint = getCoordinates(a.findViewById(listImgView.get(userPath.get(m-1))));
                coordEndPoint = getCoordinates(a.findViewById(listImgView.get(userPath.get(m))));


                if (incorrectSequence == 0) {
                    paint.setColor(GREEN);
                } else {
                    paint.setColor(RED);
                }

                canvas.drawLine((float) coordStartPoint[0], (float) coordStartPoint[1], (float) coordEndPoint[0], (float) coordEndPoint[1], paint);

            }
        }

        return bitmap;
    }

    public double[] getCoordinates(ImageView circle) {
        double[] coordinates = new double[2];

        coordinates[0] = circle.getX() + circle.getWidth()/1.25;
        coordinates[1] = circle.getY() + circle.getHeight()/1.25;

        return coordinates;
    }

}
