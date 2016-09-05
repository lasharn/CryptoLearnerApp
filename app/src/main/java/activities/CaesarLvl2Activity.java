package activities;


import android.content.Intent;
import android.support.v4.app.DialogFragment;

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
        instructionPart1 = R.string.caesar_lvl2_instr_part1;
        instructionPart2 = R.string.caesar_lvl1_instr_part2;
        targetLetterBackground = R.drawable.background_plain_letter;
        answerLetterBackground = R.drawable.background_cipher_letter;
        nextLevel = CaesarLvl3Activity.class;
        helpMessage = getResources().getString(R.string.help_caesar_lvl_1);
    }

    @Override
    protected CaesarMessage createCaesarMessage(String targetWord, int key) {
        return new CaesarMessage(targetWord, key);
    }

}
