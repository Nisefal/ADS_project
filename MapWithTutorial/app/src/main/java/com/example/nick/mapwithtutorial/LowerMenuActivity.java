package com.example.nick.mapwithtutorial;

import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

/**
 * Created by Nick on 29.08.2017.
 */

public class LowerMenuActivity extends Activity implements OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_menu);
        //LinearLayout half = findViewById(R.id.half);
    }

    public boolean onTouch(View view, MotionEvent event) {
        int H = 0, _HDelta;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                H = (int) event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                _HDelta = (int) event.getRawX();
                Toast.makeText(getApplicationContext(), "Data saved"+ H + " " + _HDelta, Toast.LENGTH_LONG).show();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    private void setFlip(ViewFlipper vf, String s){
        switch (s){
            case "owner": {
                vf.setDisplayedChild(1);
                break;
            }
            case "settings": {
                vf.setDisplayedChild(2);
                break;
            }
        }
    }
}
