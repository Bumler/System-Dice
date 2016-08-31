package com.example.isstech.systemdice;

public class die {
    int face = 0;

    public die(){
        roll();
    }

    public int getFace(){
        return face;
    }

    //generates a die with a face 1-6
    public void roll (){
        face = 1 + (int)(Math.random()*6);
    }

    public int getDamageExplode(){
        if (face == 1){
            return 0;
        }
        else if (face == 6){
            die explo = new die();
            return 2 + explo.getDamage();
        }
        else return 1;
    }

    public int getDamageDoubleExplode(){
        if (face == 1){
            return 0;
        }
        else if (face == 6){
            die explo = new die();
            return 2 + explo.getDamage();
        }
        else if (face == 5){
            die explo = new die();
            return 1 + explo.getDamage();
        }
        else return 1;
    }

    public int getDamage(){
        if (face == 1){
            return 0;
        }
        else if (face == 6){
            return 2;
        }
        else return 1;
    }
}