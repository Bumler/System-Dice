package com.example.isstech.systemdice;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.String.*;
/**
 * Created by Bulmdozer on 8/29/2015.
 */
public class MiscActivity extends Activity implements View.OnClickListener{

    private View screen;
    //intent buttons
    private Button goTo2d6;
    private Button goToPosition;
    private Button goToAttack;
    private Button goToDefense;
    private Button goToMisc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.misc_layout);

        init();
        portalInit();
        swipeLR();
    }

    private void init() {

    }
    //--------------------------------------------------------------------------------
    //The intent buttons included in every .java
    private void portalInit() {
        goToMisc = (Button) findViewById(R.id.miscIntent);
        goToMisc.setBackgroundColor(Color.YELLOW);

        //go to position
        goToPosition = (Button) findViewById(R.id.positionIntent);
        goToPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MiscActivity.this, PositionActivity.class);
                startActivity(i);
            }
        });

        //go to 2d6
        goTo2d6 = (Button) findViewById(R.id.TwoD6Intent);
        goTo2d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MiscActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        goToAttack = (Button) findViewById(R.id.attackIntent);
        goToAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MiscActivity.this, AttackActivity.class);
                startActivity(i);
            }
        });

        goToDefense = (Button) findViewById(R.id.defenseIntent);
        goToDefense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MiscActivity.this, DefenseActivity.class);
                startActivity(i);
            }
        });

//        goToMisc = (Button) findViewById(R.id.miscIntent);
//        goToMisc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MiscActivity.this, MiscActivity.class);
//                startActivity(i);
//            }
//        });
    }

    @Override
    public void onClick(View v) {

    }

    private void swipeLR(){
        screen = findViewById(R.id.layout_Main);

        screen.setOnTouchListener(new OnSwipeTouchListener(MiscActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                Intent i = new Intent(MiscActivity.this, DefenseActivity.class);
                startActivity(i);
            }
            public void onSwipeLeft() {
                Intent i = new Intent(MiscActivity.this, MainActivity.class);
                startActivity(i);
            }
            public void onSwipeBottom() {
            }

        });
    }
}
