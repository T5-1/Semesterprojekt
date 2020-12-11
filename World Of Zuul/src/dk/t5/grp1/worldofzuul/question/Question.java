package dk.t5.grp1.worldofzuul.question;

import java.io.*;
import java.nio.charset.StandardCharsets;

public abstract class Question {

    private boolean available;
    private String question;
    private String[] answers = new String[3];
    private int correctAnswer;

    public Question(String questionPath, String answer1Path, String answer2Path, String answer3Path, int correctAnswer) {
        this.correctAnswer = correctAnswer;
        question = loadText(questionPath);
        answers[0] = loadText(answer1Path);
        answers[1] = loadText(answer2Path);
        answers[2] = loadText(answer3Path);
    }

    //Loads the questions and the answers, and returns a string that either a question or answer
    public String loadText(String path) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error 404: File not found!";
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
