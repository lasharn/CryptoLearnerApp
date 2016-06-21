package unpackaged;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LevelUnlocks {

    private static LevelUnlocks instance;

    private SharedPreferences prefs;

    private static final String cryptoPreferences = "CryptoPrefs";
    private static final String lvlUnlockKey = "lvlUnlockKey";

    private LevelUnlocks(Activity a) {
        prefs = a.getSharedPreferences("com.cryptolearner.mobile.cryptolearner", Context.MODE_PRIVATE);
    }

    private static void initialize(Activity a) {
        if (instance == null) {
            instance = new LevelUnlocks(a);
        }
    }

    public boolean isUnlocked(ChallengeType type, int challengeNo) {
        int currentUnlocks = prefs.getInt(lvlUnlockKey, 1);
        if (type == ChallengeType.CAESAR) {
            return currentUnlocks >= challengeNo;
        }
        if (type == ChallengeType.SUBSTITUTION) {
            return currentUnlocks >= challengeNo + 4; // WARNING this relies on there being 4 caesar levels (which could change)
        }
        if (type == ChallengeType.VIGENERE) {
            return currentUnlocks >= challengeNo + 7;
        }
        return false;
    }

    public void levelComplete(ChallengeType type, int challengeNo) {
        int currentUnlocks = prefs.getInt(lvlUnlockKey, 1);
        SharedPreferences.Editor editor = prefs.edit();
        Log.d("Prefs", currentUnlocks + "");
        if (type == ChallengeType.CAESAR && currentUnlocks <= challengeNo) {
            editor.putInt(lvlUnlockKey, challengeNo+1).apply();
        }
        if (type == ChallengeType.SUBSTITUTION && currentUnlocks <= (challengeNo+4)) {
            // WARNING this relies on there being 4 caesar levels (which could change)
            editor.putInt(lvlUnlockKey, challengeNo+4+1).apply();
        }
        if (type == ChallengeType.VIGENERE && currentUnlocks <= (challengeNo+7)) {
            editor.putInt(lvlUnlockKey, challengeNo+7+1).apply();
        }
        int updatedUnlocks = prefs.getInt(lvlUnlockKey, -1);
        Log.d("Prefs", updatedUnlocks + "");
    }

    public static LevelUnlocks getInstance(Activity a) {
        initialize(a);
        return instance;
    }

    public void resetProgress() {
        prefs.edit().putInt(lvlUnlockKey, 1).apply();
    }

    public void unlockAll() {
        prefs.edit().putInt(lvlUnlockKey, 20).apply();
    }

}
