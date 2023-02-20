package services;

import java.util.List;
import java.util.Map;
import models.Question;

public interface QuestionService {

    public boolean addQuestion(Question question);

    public List<Map<String, String>> getRandomQuestions(int numberOfQuestions);
}
