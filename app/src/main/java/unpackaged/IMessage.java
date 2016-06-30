package unpackaged;


public interface IMessage {
    void addLetter(String letter);
    void removeLetter(String letter);
    String getSelectedString();
    boolean isCorrect();
    String getCorrectAnswer();
}
