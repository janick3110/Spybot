package com.pawns;

import com.example.spybot.R;
import com.pawns.Attack.AttackHeal;
import com.pawns.Attack.AttackSpeed;

public class Bug extends Pawn {

    public Bug() {
        this.team = 0;
        this.name = "Bug";
        this.speed = 6;
        this.leftSteps = this.speed;
        this.maxSize = 3;

        this.pictureHead = R.drawable.button_icon_bug;
        this.pictureTail = R.drawable.button_icon_bug;

        this.attack1 = new AttackHeal("Byte", R.drawable.highlighting_attack,(byte) 3, (byte) -3);
        this.attack2 = new AttackSpeed("Bite",R.drawable.highlighting_attack,(byte) 1, (byte) -1);

    }

}
