package com.pawns.Attack;

import com.example.spybot.MainActivity;
import com.level.Field;
import com.pawns.Pawn;
import com.pawns.PawnSegment;

import static java.lang.Math.abs;

public class AttackHeal extends Attack{


    public AttackHeal(String attackName,int icon,  byte range, byte magnitude) {
        super(attackName,icon, range, magnitude);
    }

    @Override
    public void performAttack(MainActivity m, Field target) {

        Pawn targetPawn = target.getSegment().getPawn();

        if(magnitude < 0) {
            for(int i = 0; i < abs(magnitude); i++) {
                if(targetPawn.getSegments().size() > 1){
                    PawnSegment segment = targetPawn.getSegments().get(1);
                    Field segField = segment.getField();
                    segField.setSegment(null);
                    targetPawn.getSegments().remove(1);
                    m.refreshBoard();


                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    targetPawn.die();
                    continue;
                }

            }
        }
    }
}
