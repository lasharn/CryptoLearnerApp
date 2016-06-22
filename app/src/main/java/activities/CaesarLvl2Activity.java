package activities;


import com.cryptolearner.mobile.cryptolearner.R;

import caesar_encryption.CaesarMessage;
import caesar_encryption.CaesarPartiallyCompleteMessage;

/**
 * The activity for level 2 of the caesar challenges
 * In this level players must encrypt an entire word
 */
public class CaesarLvl2Activity extends CaesarBaseLvlActivity {

    @Override
    void setupFields() {
        challengeNo = 2;
        layoutId = R.layout.activity_caesar_lvl;
    }

    @Override
    protected CaesarMessage createCaesarMessage(String targetWord, int key) {
        return new CaesarMessage(targetWord, key);
    }

}
