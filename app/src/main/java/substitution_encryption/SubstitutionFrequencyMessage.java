package substitution_encryption;

import unpackaged.IMessage;


public class SubstitutionFrequencyMessage implements IMessage {

    private String eta = "ETA";
    private IMessage ETA;
    private IMessage sentence;
    private int letterCount = 0;
    private SubstitutionMappings subMap;

    public SubstitutionFrequencyMessage(String targetWord, SubstitutionMappings mappings) {
        ETA = new SubstitutionMessage(eta, mappings);
        sentence = new SubstitutionMessage(targetWord, mappings);
        subMap = mappings;
    }


    @Override
    public void addLetter(String letter) {
        if (letterCount >= 3) {
            return;
        }
        ETA.addLetter(letter);
        // needs to add the corresponding plain text letter TODO doesn't map to correct letter
        sentence.addLetter(subMap.getLetter(letter));
        letterCount++;
    }

    @Override
    public void removeLetter(String letter) {
        if (letterCount <= 0) {
            return;
        }
        letterCount--;
        ETA.removeLetter(letter);
        sentence.removeLetter(Character.toString(eta.charAt(letterCount)));
    }

    @Override
    public String getSelectedString() {
        return ETA.getSelectedString();
    }

    @Override
    public boolean isCorrect() {
        return ETA.isCorrect();
    }

    @Override
    public String getCorrectAnswer() {
        return ETA.getCorrectAnswer();
    }

    @Override
    public String plainTextString() {
        return ETA.plainTextString();
    }

    @Override
    public boolean isFull() {
        return ETA.isFull();
    }

    public String selectedSentence() {
        return sentence.getSelectedString();
    }

    public String answerSentence() {
        return sentence.getCorrectAnswer();
    }
}
