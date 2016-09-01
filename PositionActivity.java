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
 * Created by isstech on 8/11/2015.
 */
public class PositionActivity extends Activity implements View.OnClickListener {


    private Button btnRoll3d6;

    private TextView Res1;
    private TextView Res2;
    private TextView Res3;
    private TextView Total;
    private TextView ResultName;
    private TextView ResultMod;

    //intent buttons
    private Button goTo2d6;
    private Button goToPosition;
    private Button goToAttack;
    private Button goToDefense;
    private Button goToMisc;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.position_activity);

        init();
        portalInit();
    }

    private void init() {
        btnRoll3d6 = (Button)findViewById(R.id.btnRoll3d6);

        Res1 = (TextView)findViewById(R.id.Res1);
        Res2 = (TextView)findViewById(R.id.Res2);
        Res3 = (TextView)findViewById(R.id.Res3);
        Total = (TextView)findViewById(R.id.Total);

        ResultName = (TextView)findViewById(R.id.ResultName);
        ResultMod = (TextView)findViewById(R.id.ResultMod);

        btnRoll3d6.setOnClickListener(this);
    }

    @Override
    public void onClick (View view)
    {
        die die1 = new die();
        die die2 = new die();
        die die3 = new die();
        int dieTotal = die1.getFace() + die2.getFace() + die3.getFace();

        Res1.setText(""+die1.getFace()+"");
        Res2.setText(""+die2.getFace()+"");
        Res3.setText(""+die3.getFace()+"");
        Total.setText(valueOf(dieTotal));

        if(dieTotal == 3 || dieTotal == 18)
        {ResultName.setText("Crotch Shot!");
            ResultMod.setText("Crit!");}

        else if(dieTotal == 4 || dieTotal == 17)
        {ResultName.setText("Monstrous");
            ResultMod.setText("Half Crit");}

        else if(dieTotal == 5 || dieTotal == 16)
        {ResultName.setText("Devastating");
            ResultMod.setText("+3");}

        else if(dieTotal == 6 || dieTotal == 15)
        {ResultName.setText("Strong");
            ResultMod.setText("+2");}

        else if(dieTotal == 7 || dieTotal == 8 || dieTotal == 9 || dieTotal == 12 || dieTotal == 13 || dieTotal == 14)
        {ResultName.setText("Good");
            ResultMod.setText("+1");}

        else if(dieTotal == 10 || dieTotal == 11)
        {ResultName.setText("Average");
            ResultMod.setText("+0");}
    }


    //--------------------------------------------------------------------------------
    //The intent buttons included in every .java
    private void portalInit() {
        goToPosition = (Button) findViewById(R.id.positionIntent);
        goToPosition.setBackgroundColor(Color.YELLOW);
//        //go to position
//        goToPosition = (Button) findViewById(R.id.positionIntent);
//        goToPosition.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(PositionActivity.this, PositionActivity.class);
//                startActivity(i);
//            }
//        });

        //go to 2d6
        goTo2d6 = (Button) findViewById(R.id.TwoD6Intent);
        goTo2d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PositionActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        goToAttack = (Button) findViewById(R.id.attackIntent);
        goToAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PositionActivity.this, AttackActivity.class);
                startActivity(i);
            }
        });

        goToDefense = (Button) findViewById(R.id.defenseIntent);
        goToDefense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PositionActivity.this, DefenseActivity.class);
                startActivity(i);
            }
        });

        goToMisc = (Button) findViewById(R.id.miscIntent);
        goToMisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PositionActivity.this, MiscActivity.class);
                startActivity(i);
            }
        });
    }
}
