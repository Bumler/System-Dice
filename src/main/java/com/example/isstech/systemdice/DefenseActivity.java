package com.example.isstech.systemdice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

/**
 * Created by isstech on 8/24/15.
 */
public class DefenseActivity extends Activity implements View.OnClickListener
{

    private EditText dice;
    int D;

    private TextView dTotal;

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
        setContentView(R.layout.defense_activity);

        init();
        portalInit();
    }

    private void init() {
        btnRollD = (Button)findViewById(R.id.btnRollD);
        dice = (EditText)findViewById(R.id.damageInput);
            SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
            String numDice = sharedPreferences.getString("def","");
            dice.setText(numDice);

        dTotal = (TextView)findViewById(R.id.dTotal);

        btnRollD.setOnClickListener(this);
    }

    private void update(String dieNum){
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("def", dieNum);
        editor.commit();
    }

    private void portalInit() {
        //go to position
        goToPosition = (Button) findViewById(R.id.positionIntent);
        goToPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DefenseActivity.this, PositionActivity.class);
                startActivity(i);
            }
        });

        //go to 2d6
        goTo2d6 = (Button) findViewById(R.id.TwoD6Intent);
        goTo2d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DefenseActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        goToAttack = (Button) findViewById(R.id.attackIntent);
        goToAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DefenseActivity.this, AttackActivity.class);
                startActivity(i);
            }
        });

        goToDefense = (Button) findViewById(R.id.defenseIntent);
        goToDefense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DefenseActivity.this, DefenseActivity.class);
                startActivity(i);
            }
        });

        goToMisc = (Button) findViewById(R.id.miscIntent);
        goToMisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DefenseActivity.this, MiscActivity.class);
                startActivity(i);
            }
        });
    }
    
    @Override
    public void onClick(View v) {
        String dieNum = dice.getText().toString();
        update(dieNum);
        //set dice to 0 if no number is given
        if(dice.getText().length() == 0)
        {
            dieNum = "0";
        }
        int six = 0;
        int oth = 0;
        int one = 0;
        //number of defense dice
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
        //totals the points of damage with 6's being worth 2
        int defTotal =0;
        defTotal = (six*2)+oth;
        dTotal.setText(valueOf(defTotal));

        //the first line of toast creates a message that will breifly (1second) display-
        //on button press. The gravity sets the messages position with the second and-
        //the third input being x and y offset respectively. The last method shows it
        Toast toast = Toast.makeText(this, "Roll Successful", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0,450);
        toast.show();
    }
}