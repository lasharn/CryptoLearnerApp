package general;


public interface IMessage {
    void addLetter(String letter);
    void removeLetter(String letter);
    String getSelectedString();
    boolean isCorrect();
    String getCorrectAnswer();
    String plainTextString();
    boolean isFull();
}
