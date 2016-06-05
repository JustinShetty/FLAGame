package com.justin.flagame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Main gameplay area
 */

public class ArenaActivity extends Activity{
    Random rand = new Random();

    ImageView mainFlag;
    TextView score;
    TextView lives;
    Button[] buttons = new Button[4];
    int scoreVal = 0;
    int livesVal = 3;
    String curCountry;
    String[] options = new String[4];

    HashMap<String, String> countries = new HashMap<>();
    String[] fileNames;
    ArrayList<String> workingList = new ArrayList<>();

    int correctLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);
        getFiles();
        sceneInit();
    }

    public void sceneInit(){
        mainFlag = (ImageView) this.findViewById(R.id.countryToGuess);
        score = (TextView) this.findViewById(R.id.score);
        lives = (TextView) this.findViewById(R.id.lives);
        buttons[0] = (Button) this.findViewById(R.id.choice1);
        buttons[1] = (Button) this.findViewById(R.id.choice2);
        buttons[2] = (Button) this.findViewById(R.id.choice3);
        buttons[3] = (Button) this.findViewById(R.id.choice4);
        buildMapBuildList();
        update();
    }

    public void update(){
        score.setText(String.valueOf(scoreVal));
        lives.setText(String.valueOf(livesVal));
        if(workingList.size()>=4){
            buildScene();
            mainFlag.setImageBitmap(loadFlag(countries.get(curCountry)));
            ((TextView) findViewById(R.id.ANSWER)).setText(curCountry);
        }
        else{
            Toast.makeText(this,"No more countries",Toast.LENGTH_SHORT).show();
            Intent goToMenu = new Intent(this,MainActivity.class);
            startActivity(goToMenu);
        }
    }

    public void buildScene(){
        ArrayList<String> temp = new ArrayList<>();
        for(String s : workingList){
            temp.add(s);
        }
        Collections.shuffle(temp);
        for(int i = 0 ; i < options.length ; i++){
            options[i] = temp.get(i);
        }

        correctLoc = rand.nextInt(4);
        curCountry = options[correctLoc];

        for(int i = 0 ; i < options.length ; i++){
            buttons[i].setText(options[i]);
        }
        for(String s : workingList){
            System.out.println(s);
        }
    }

    public void getFiles(){
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
        return result.replace("_"," ");
    }

    public void buildMapBuildList(){
        for(String name : fileNames){
            countries.put(countryFromFileName(name), name);
            workingList.add(countryFromFileName(name));
        }
    }

    public void choose(View view) {
        int id = view.getId();
        if(id == buttons[correctLoc].getId()){
//            Toast.makeText(this,"right",Toast.LENGTH_SHORT).show();
            scoreVal += 100;
            String countryToRemove = String.valueOf(buttons[correctLoc].getText());
            workingList.remove(countryToRemove);
            update();
        }
        else{
//            Toast.makeText(this,"wrong",Toast.LENGTH_SHORT).show();
            livesVal -= 1;
            update();
        }
    }
}
