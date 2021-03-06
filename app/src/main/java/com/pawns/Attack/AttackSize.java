package com.pawns.Attack;

import com.example.spybot.MainActivity;
import com.level.Field;
import com.pawns.Pawn;

public class AttackSize extends Attack {

    public AttackSize(String attackName,int icon,  byte range, byte magnitude) {
        super(attackName,icon,  range, magnitude);
    }

    @Override
    public void performAttack(MainActivity m, Field target) {
        target.getSegment().getPawn().setMaxSize((byte) (target.getSegment().getPawn().getMaxSize() + magnitude));
    }
}
