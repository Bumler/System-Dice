package com.example.isstech.systemdice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.*;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btnRoll2d6;

    private TextView RawResultsText;
    private TextView GrandTotal;
    private TextView CritField;

    private EditText txtModifier;

    private View screen;
    //---------------------------
    //Portal variables
    private Button goTo2d6;
    private Button goToPosition;
    private Button goToAttack;
    private Button goToDefense;
    private Button goToMisc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        portalInit();
        swipeLR();
    }

    private void init()
    {
        btnRoll2d6 = (Button)findViewById(R.id.btnRoll2d6);

        RawResultsText = (TextView)findViewById(R.id.RawResultsText);
        GrandTotal = (TextView)findViewById(R.id.GrandTotal);
        CritField = (TextView)findViewById(R.id.CritField);

        txtModifier = (EditText)findViewById(R.id.txtModifier);
            SharedPreferences sharedPreferences=getSharedPreferences("MyData",Context.MODE_PRIVATE);
            String numDice = sharedPreferences.getString("modVal","");
            txtModifier.setText(numDice);

        btnRoll2d6.setOnClickListener(this);
    }

    //--------------------------------------------------------------------------------
    //The intent buttons included in every .java
    private void portalInit()
    {
        goTo2d6 = (Button) findViewById(R.id.TwoD6Intent);
        goTo2d6.setBackgroundColor(Color.YELLOW);

        goToPosition = (Button) findViewById(R.id.positionIntent);
        goToPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PositionActivity.class);
                startActivity(i);
            }
        });

        goToAttack = (Button) findViewById(R.id.attackIntent);
        goToAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AttackActivity.class);
                startActivity(i);
            }
        });

        goToDefense = (Button) findViewById(R.id.defenseIntent);
        goToDefense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DefenseActivity.class);
                startActivity(i);
            }
        });

        goToMisc = (Button) findViewById(R.id.miscIntent);
        goToMisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MiscActivity.class);
                startActivity(i);
            }
        });
    }

    private void update(String dieNum){
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("modVal", dieNum);
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        String modVal = txtModifier.getText().toString();
        update(modVal);
        if(txtModifier.getText().length() == 0)
        {
            modVal = "0";
        }
        int mod = Integer.parseInt(modVal);
        die die1 = new die();
        die die2 = new die();
        int total = mod + die1.getFace() + die2.getFace();

        GrandTotal.setText("Total: " + valueOf(total));
        RawResultsText.setText("Raw Results    " + die1.getFace() + " + " + die2.getFace());

        if (die1.getFace() == 6 && die2.getFace() == 6) {
            CritField.setText("Crit!");
        } else if (die1.getFace() == 6 && die2.getFace() == 5) {
            CritField.setText(("Mini-Crit!"));
        } else if (die1.getFace() == 6 && die2.getFace() == 4) {
            CritField.setText(("Mini-Crit!"));
        }else if (die1.getFace() == 5 && die2.getFace() == 6) {
            CritField.setText(("Mini-Crit!"));
        }else if (die1.getFace() == 4 && die2.getFace() == 6) {
            CritField.setText(("Mini-Crit!"));
        }
        else if (die1.getFace() == 1 && die2.getFace() == 1){
            CritField.setText("Double Ones!");
        }
        else{CritField.setText((""));}

        //the first line of toast creates a message that will breifly (1second) display-
        //on button press. The gravity sets the messages position with the second and-
        //the third input being x and y offset respectively. The last method shows it
        Toast toast = Toast.makeText(this, "Roll Successful", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0,-200);
        toast.show();
    }

    private void swipeLR(){
        screen = findViewById(R.id.layout1);

        screen.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
                Intent i = new Intent(MainActivity.this, PositionActivity.class);
                startActivity(i);
            }
            public void onSwipeBottom() {
            }

        });
    }

}
