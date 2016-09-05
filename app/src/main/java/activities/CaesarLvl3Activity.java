package activities;


import android.content.Intent;
import android.support.v4.app.DialogFragment;

import com.cryptolearner.mobile.cryptolearner.R;

import caesar_encryption.CaesarGivenLetterMessage;
import caesar_encryption.CaesarMessage;
import caesar_encryption.CaesarPartiallyCompleteMessage;

/**
 * The activity for level 3 of the caesar challenges
 * In this level players must decrypt a word without knowing the key
 * they are given one decrypted letter
 */
public class CaesarLvl3Activity extends CaesarBaseLvlActivity {

    @Override
    void setupFields() {
        challengeNo = 3;
        layoutId = R.layout.activity_caesar_lvl;
        instructionPart1 = R.string.caesar_lvl1_instr_part1;
        instructionPart2 = R.string.caesar_lvl3_instr_part2;
        targetLetterBackground = R.drawable.background_plain_letter;
        answerLetterBackground = R.drawable.background_cipher_letter;
        nextLevel = CaesarLvl4Activity.class;
        helpMessage = getResources().getString(R.string.help_caesar_lvl_1);
    }

    @Override
    protected CaesarMessage createCaesarMessage(String targetWord, int key) {
        return new CaesarGivenLetterMessage(targetWord, key);
    }

    @Override
    protected String getInstructionText(String targetWord, int key) {
        return getString(instructionPart1) + " " + targetWord + " " +
                getString(instructionPart2);
    }

}
