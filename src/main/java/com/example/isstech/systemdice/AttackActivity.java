package com.example.isstech.systemdice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private Button explode;
    private Button crit;
    private Button halfCrit;

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
        //initializes the buttons to go to other activities
        portalInit();
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

        goToAttack = (Button) findViewById(R.id.attackIntent);
        goToAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AttackActivity.this, AttackActivity.class);
                startActivity(i);
            }
        });

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

    int six = 0;//worth 2
    int one = 0;//worth 0
    int oth = 0;//worth 1

    int sixX = 0;
    @Override
    public void onClick(View view)
    {
        String dieNum = dice.getText().toString();
        update(dieNum);
        //set dice to 0 if no number is given
        if(dice.getText().length() == 0)
        {
            dieNum = "0";
        }
        //number of attack dice
        D = Integer.parseInt(dieNum);
        //setting up the three damage values
        six = 0;//worth 2
        one = 0;//worth 0
        oth = 0;//worth 1

        //sets up the one die we will use
        int die;
        //for loop rolls a six sided die until it rolls D times and totals the number of sixes ones and others
        for(int k = 0; k < D; k++)
        {
            die = 1+ (int) (Math.random() * 6);
            if (die == 1)
            {one++;}
            else if (die == 6)
            {six++;}
            else {oth++;}
        }
        sixX = six;
        //totals the points of damage with 6's being worth 2
        int damTotal =0;
        damTotal = (six*2)+oth;
        damageBreakdown.setText("1s:["+valueOf(one)+"]  2-5s:["+valueOf(oth)+"]  6s: ["+valueOf(six)+"]");
        dTotal.setText(valueOf(damTotal));
        //Toast.makeText(this, "Roll Successful", Toast.LENGTH_LONG).show();

        explode = (Button) findViewById(R.id.Explode);
        explode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int die;
                int sixX2;
                int explo = 0;

                while (sixX != 0) {
                    sixX2 = sixX;
                    sixX = 0;
                    for (int k = 0; k < sixX2; k++) {
                        die = 1 + (int) (Math.random() * 6);
                        if (die == 1) {
                            one++;
                        } else if (die == 6) {
                            six++;
                            sixX++;
                        } else {
                            oth++;
                        }
                    }
                    explo++;
                }
                int damTotal = (six*2)+oth;
                damageBreakdown.setText("1s:["+valueOf(one)+"]  2-5s:["+valueOf(oth)+"]  6s: ["+valueOf(six)+"] Explosions: [" +valueOf(explo)+"]");
                if (explo == 0)
                {
                    damageBreakdown.setText("Can't Explode");
                }
                dTotal.setText(valueOf(damTotal));
                //Toast.makeText(this,"Mini-Crit Complete",Toast.LENGTH_LONG).show();
            }
        });

        halfCrit = (Button) findViewById(R.id.hCrit);
        halfCrit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int die;
                int hD;
                if (D%2 == 1)
                {
                    hD = D/2 + 1;
                }
                else{hD=D/2;}
                for(int k = 0; k < hD; k++)
                {
                    die = 1+ (int) (Math.random() * 6);
                    if (die == 1)
                    {one++;}
                    else if (die == 6)
                    {six++;}
                    else {oth++;}
                }
                sixX = six;
                //totals the points of damage with 6's being worth 2
                int damTotal =0;
                damTotal = (six*2)+oth;
                damageBreakdown.setText("1s:["+valueOf(one)+"]  2-5s:["+valueOf(oth)+"]  6s: ["+valueOf(six)+"]");
                dTotal.setText(valueOf(damTotal));
                //Toast.makeText(this,"Half-Crit Complete",Toast.LENGTH_LONG).show();
            }
        });
        crit = (Button) findViewById(R.id.Crit);
        crit.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick (View v){
                int die;
                for(int k = 0; k < D; k++)
                {
                    die = 1+ (int) (Math.random() * 6);
                    if (die == 1)
                    {one++;}
                    else if (die == 6)
                    {six++;}
                    else {oth++;}
                }
                sixX = six;
                //totals the points of damage with 6's being worth 2
                int damTotal =0;
                damTotal = (six*2)+oth;
                damageBreakdown.setText("1s:["+valueOf(one)+"]  2-5s:["+valueOf(oth)+"]  6s: ["+valueOf(six)+"]");
                dTotal.setText(valueOf(damTotal));
                //Toast.makeText(this,"Crit Complete",Toast.LENGTH_LONG).show();
            }
        });
    }


}
