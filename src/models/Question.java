package models;

public class Question {

    public final String question;
    public final String optionA;
    public final String optionB;
    public final String optionC;
    public final String optionD;
    public final String answer;

    public Question(String question, String optionA, String optionB, String optionC, String optionD, String answer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }
}
