package com.justin.flagame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Main gameplay area
 */

public class ArenaActivity extends Activity{
    ImageView mainFlag;
    TextView score;
    TextView lives;
    Button choice1;
    Button choice2;
    Button choice3;
    Button choice4;
    private int scoreVal = 0;
    private int livesVal = 3;

    HashMap<String, String> countries = new HashMap<String, String>();
    String[] fileNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);
        prepareScene();
//        Intent getDataFromMain = getIntent();
//        String data = getDataFromMain.getExtras().getString("time");
//        TextView tv2 = (TextView) findViewById(R.id.tv2);
//        tv2.setText(data);
    }

    public void prepareScene(){
        mainFlag = (ImageView) this.findViewById(R.id.countryToGuess);
        mainFlag.setImageBitmap(loadFlag("azerbaijan.svg.png"));
        score = (TextView) this.findViewById(R.id.score);
        lives = (TextView) this.findViewById(R.id.lives);
        choice1 = (Button) this.findViewById(R.id.choice1);
        choice2 = (Button) this.findViewById(R.id.choice2);
        choice3 = (Button) this.findViewById(R.id.choice3);
        choice4 = (Button) this.findViewById(R.id.choice4);
    }

    public void UpdateVals(){
        score.setText(String.valueOf(scoreVal));
        lives.setText(String.valueOf(livesVal));
    }

    public void getFiles(View view){
        try{
            fileNames = getAssets().list("flags");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap loadFlag(String name){
        AssetManager am = this.getAssets();
        try{
            return BitmapFactory.decodeStream(am.open("flags/"+name));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String countryFromFileName(String fileName){
        String result = fileName.substring(0,1).toUpperCase();
        result += fileName.split("\\.")[0].substring(1);
        return result;
    }

    public void buildHashMap(){
        for(String name : fileNames){
            countries.put(countryFromFileName(name), name);
        }
    }

//    public void testFunction(View view) {
//        getFiles(view);
//        buildHashMap();
//        for(String country : countries.keySet()) {
//            System.out.println(country);
//            System.out.println(countries.get(country));
//        }
//    }
}
