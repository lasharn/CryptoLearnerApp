package activities;


import android.content.Intent;
import android.support.v4.app.DialogFragment;

import com.cryptolearner.mobile.cryptolearner.R;

import caesar_encryption.CaesarMessage;
import caesar_encryption.CaesarPartiallyCompleteMessage;
import unpackaged.ChallengeType;

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
        instructionPart1 = R.string.caesar_lvl1_instr_part1;
        instructionPart2 = R.string.caesar_lvl1_instr_part2;
        targetLetterBackground = R.drawable.background_cipher_letter;
        answerLetterBackground = R.drawable.background_plain_letter;
        nextLevel = CaesarLvl2Activity.class;
    }

    @Override
    protected CaesarMessage createCaesarMessage(String targetWord, int key) {
        return new CaesarPartiallyCompleteMessage(targetWord, key, 2);
    }

}
