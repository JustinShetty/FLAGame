package com.justin.flagame;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class Menu extends AppCompatActivity {

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
        Intent goToArena = new Intent(this,PlayArea.class);
        startActivity(goToArena);
    }

    public void quitMethod(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
