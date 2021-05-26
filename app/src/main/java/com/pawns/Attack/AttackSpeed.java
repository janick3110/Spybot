package com.pawns.Attack;

import com.example.spybot.MainActivity;
import com.level.Field;
import com.pawns.Pawn;

public class AttackSpeed extends Attack{

    public AttackSpeed(String attackName,int icon,  byte range, byte magnitude) {
        super(attackName,icon,  range, magnitude);
    }

    @Override
    public void performAttack(MainActivity m, Field target) {
        Pawn targetPawn = target.getSegment().getPawn();
        targetPawn.setSpeed((byte) (targetPawn.getSpeed() + magnitude));
        targetPawn.setLeftSteps((byte) (targetPawn.getLeftSteps() + magnitude));
    }
}
