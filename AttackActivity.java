package com.example.isstech.systemdice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import static java.lang.String.*;
/**
 * Created by isstech on 8/14/2015.
 */
public class AttackActivity extends Activity implements View.OnClickListener
{

    private EditText dice;
    int D;

    private TextView damageBreakdown;
    private TextView dTotal;

    private View screen;

    private Button explode;
    private Button crit;
    private Button halfCrit;

    private Button p1;
    private Button p2;
    private Button p3;
    private Button m1;
    private Button m2;
    private Button m3;

    private Button btnRollD;

    //intent buttons
    private Button goTo2d6;
    private Button goToPosition;
    private Button goToAttack;
    private Button goToDefense;
    private Button goToMisc;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attack_activity);

        //initializes the buttons
        init();
        dModifiers();
        //initializes the buttons to go to other activities
        portalInit();

        swipeLR();
    }

    private void init()
    {
        dice = (EditText)findViewById(R.id.damageInput);
            SharedPreferences sharedPreferences=getSharedPreferences("MyData",Context.MODE_PRIVATE);
            String numDice = sharedPreferences.getString("dice","");
            dice.setText(numDice);

        btnRollD = (Button)findViewById(R.id.btnRollD);
        damageBreakdown = (TextView)findViewById(R.id.damageBreakdown);
        dTotal = (TextView)findViewById(R.id.dTotal);
        btnRollD.setOnClickListener(this);
    }

    private void update(String dieNum){
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dice", dieNum);
        editor.commit();
    }

    private void portalInit() {
        goToAttack = (Button) findViewById(R.id.attackIntent);
        goToAttack.setBackgroundColor(Color.YELLOW);
        //go to position
        goToPosition = (Button) findViewById(R.id.positionIntent);
        goToPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AttackActivity.this, PositionActivity.class);
                startActivity(i);
            }
        });

        //go to 2d6
        goTo2d6 = (Button) findViewById(R.id.TwoD6Intent);
        goTo2d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AttackActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

//        goToAttack = (Button) findViewById(R.id.attackIntent);
//        goToAttack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(AttackActivity.this, AttackActivity.class);
//                startActivity(i);
//            }
//        });

        goToDefense = (Button) findViewById(R.id.defenseIntent);
        goToDefense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AttackActivity.this, DefenseActivity.class);
                startActivity(i);
            }
        });

        goToMisc = (Button) findViewById(R.id.miscIntent);
        goToMisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AttackActivity.this, MiscActivity.class);
                startActivity(i);
            }
        });
    }

    //end of initializations

    dice dDice;

    boolean first = true;
    @Override
    public void onClick(View view)
    {
        checkD();
//        String dieNum = dice.getText().toString();
//        update(dieNum);
//
//        if (!first){
//            explode.setEnabled(true);
//        }
//
//        //set dice to 0 if no number is given
//        if(dice.getText().length() == 0)
//        {
//            dieNum = "0";
//        }
//
//        first = false;
//
//        //number of attack dice
//        D = Integer.parseInt(dieNum);

        //dice by default are set to no explosions
        dDice = new dice(D, 0);
        int damTotal = dDice.totalDamage();
        damageBreakdown.setText(dDice.getDamageBreakdown());
        dTotal.setText(valueOf(damTotal));

        //Toast.makeText(this, "Roll Successful", Toast.LENGTH_LONG).show();

        explode = (Button) findViewById(R.id.Explode);
        explode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dDice.setExplosion(1);
                int damTotal = dDice.totalDamage();

                damageBreakdown.setText(dDice.getDamageBreakdown());
                dTotal.setText(valueOf(damTotal));
                //Toast.makeText(this,"Mini-Crit Complete",Toast.LENGTH_LONG).show();
                explode.setEnabled(false);
            }
        });

        halfCrit = (Button) findViewById(R.id.hCrit);
        halfCrit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dDice.halfCrit();

                int damTotal = dDice.totalDamage();
                damageBreakdown.setText(dDice.getDamageBreakdown());
                dTotal.setText(valueOf(damTotal));
                //Toast.makeText(this,"Half-Crit Complete",Toast.LENGTH_LONG).show();

            }
        });

        crit = (Button) findViewById(R.id.Crit);
        crit.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick (View v){
                dDice.crit();

                int damTotal = dDice.totalDamage();
                damageBreakdown.setText(dDice.getDamageBreakdown());
                dTotal.setText(valueOf(damTotal));
                //Toast.makeText(this,"Crit Complete",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void dModifiers(){
        checkD();

        p1 = (Button) findViewById(R.id.p1);
        p1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                checkD();
                dice.setText(valueOf(D+1));
            }
        });
        p2 = (Button) findViewById(R.id.p2);
        p2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                checkD();
                dice.setText(valueOf(D+2));
            }
        });
        p3 = (Button) findViewById(R.id.p3);
        p3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                checkD();
                dice.setText(valueOf(D+3));
            }
        });
        m1 = (Button) findViewById(R.id.m1);
        m1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                checkD();
                dice.setText(valueOf(D-1));
            }
        });
        m2 = (Button) findViewById(R.id.m2);
        m2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                checkD();
                dice.setText(valueOf(D-2));
            }
        });
        m3 = (Button) findViewById(R.id.m3);
        m3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                checkD();
                dice.setText(valueOf(D-3));
            }
        });
    }

    private void checkD(){
        String dieNum = dice.getText().toString();
        update(dieNum);

        //set dice to 0 if no number is given
        if(dice.getText().length() == 0)
        {
            dieNum = "0";
        }

        //number of attack dice
        D = Integer.parseInt(dieNum);
    }

    private void swipeLR(){
        screen = findViewById(R.id.layout_Main);

        screen.setOnTouchListener(new OnSwipeTouchListener(AttackActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
                Intent i = new Intent(AttackActivity.this, PositionActivity.class);
                startActivity(i);
            }
            public void onSwipeLeft() {
                Intent i = new Intent(AttackActivity.this, DefenseActivity.class);
                startActivity(i);
            }
            public void onSwipeBottom() {
            }

        });
    }
}
