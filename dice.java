package com.example.isstech.systemdice;

import java.util.ArrayList;

public class dice {
    ArrayList<die> DiceList = new ArrayList<die>();
    int explode;

    int n1 = 0;
    int n6 = 0;
    int nOth = 0;

    // generates 'inp' number of dice and holds them in a list
    // explode is used for mini crits,
    // a 1 = a standard minicrit 2 = double
    public dice(int inp, int explode) {
        this.explode = explode;

        for (int i = 0; i < inp; i++) {
            DiceList.add(new die());
        }
    }

    public void setExplosion(int i){
        explode = i;
    }

    public int totalDamage() {
        int total = 0;
        if (explode == 1) {
            for (int i = 0; i < DiceList.size(); i++){
                if (DiceList.get(i).getFace() == 6){DiceList.add(new die());}
                total += DiceList.get(i).getDamage();
            }
        }
        else if (explode == 2){
            for (int i = 0; i < DiceList.size(); i++){
                if (DiceList.get(i).getFace() == 6 || DiceList.get(i).getFace() == 5){DiceList.add(new die());}
                total += DiceList.get(i).getDamage();
            }
        }
        else{
            for (die Die : DiceList) {
                total += Die.getDamage();
            }
        }
        return total;
    }

    private void updateDamage(){
        n1 = 0;
        n6 = 0;
        nOth = 0;

        for (die Die : DiceList) {
            if (Die.getFace() == 6) {
                n6++;
            } else if (Die.getFace() == 1) {
                n1++;
            } else {
                nOth++;
            }
        }
    }

    public String getDamageBreakdown(){
        updateDamage();
        return ("1s:[" + n1 + "]  2-5s:[" + nOth + "]  6s: [" + n6 + "]");
    }

    public void halfCrit(){
        int totalHalfCrit = (int)(.5+(DiceList.size()*1.5));
        while(DiceList.size() < totalHalfCrit){
            die extra = new die();
            DiceList.add(extra);
        }
    }

    public void crit(){
        int totalCrit = (DiceList.size() * 2);
        while(DiceList.size() < totalCrit){
            die extra = new die();
            DiceList.add(extra);
        }
    }

}
