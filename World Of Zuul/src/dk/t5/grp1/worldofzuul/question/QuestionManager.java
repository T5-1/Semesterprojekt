package dk.t5.grp1.worldofzuul.question;

public class QuestionManager {

    private Question[] questions = new Question[8];

    public QuestionManager() {
        questions[0] = new Question1();
        questions[1] = new Question2();
        questions[2] = new Question3();
        questions[3] = new Question4();
        questions[4] = new Question5();
        questions[5] = new Question6();
        questions[6] = new Question7();
        questions[7] = new Question8();
    }

    public Question[] getQuestions() {
        return questions;
    }

}
