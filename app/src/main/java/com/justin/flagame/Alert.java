package com.justin.flagame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Justin on 6/5/2016.
 */
public class Alert extends Activity {
    TextView alertTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.2));

        alertTV = (TextView) this.findViewById(R.id.alertTV);

        Intent callingActivity = getIntent();
        buildMessage(callingActivity.getIntExtra("state",0));

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                goToMenu();
                break;
        }
        return true;
    }

    public void buildMessage(int state){
        if(state==0){
            alertTV.setText("You made it through all the flags!");
        }
        else if(state == -1){
            alertTV.setText("Sorry! All out of lives.");
        }
    }

    public void goToMenu() {
        startActivity(new Intent(this,Menu.class));
    }
}
