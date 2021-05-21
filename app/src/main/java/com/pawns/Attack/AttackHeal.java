package com.pawns.Attack;

import com.level.Field;
import com.pawns.Pawn;
import com.pawns.PawnSegment;

import static java.lang.Math.abs;

public class AttackHeal extends Attack{


    public AttackHeal(byte range, byte magnitude) {
        super(range, magnitude);
    }

    @Override
    public void performAttack(Pawn target) {


        if(magnitude < 0) {
            for(int i = 0; i < abs(magnitude); i++) {
                if(target.getSegments().size() > 1){
                    PawnSegment segment = target.getSegments().get(1);
                    Field segField = segment.getField();
                    segField.setSegment(null);
                    target.getSegments().remove(1);
                }
                else {
                    //TODO Figur löschen
                    continue;
                }

            }
        }
    }
}