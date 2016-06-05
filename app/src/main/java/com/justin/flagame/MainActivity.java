package com.justin.flagame;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        buildHero();
    }
    public void buildHero(){
        ImageView hero = (ImageView) findViewById(R.id.hero);
        hero.setImageBitmap(loadImageFromAssets("un.png"));
    }

    public Bitmap loadImageFromAssets(String name){
        AssetManager am = this.getAssets();
        try{
            return BitmapFactory.decodeStream(am.open(name));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void playMethod(View view) {
        Intent goToArena = new Intent(this,ArenaActivity.class);
        startActivity(goToArena);
    }

    public void quitMethod(View view) {
        finish();
    }
}
