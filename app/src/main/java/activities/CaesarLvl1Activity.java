package activities;


import com.cryptolearner.mobile.cryptolearner.R;

import caesar_encryption.CaesarMessage;
import caesar_encryption.CaesarPartiallyCompleteMessage;

/**
 * The activity for level 1 of the caesar challenges
 * In this level players must encrypt 2 letters of a word (all other letters are already filled in)
 * The wheel should be set to the correct key already
 */
public class CaesarLvl1Activity extends CaesarBaseLvlActivity {

    @Override
    void setupFields() {
        challengeNo = 1;
        layoutId = R.layout.activity_caesar_lvl;
    }

    @Override
    protected CaesarMessage createCaesarMessage(String targetWord, int key) {
        return new CaesarPartiallyCompleteMessage(targetWord, key, 2);
    }

}
