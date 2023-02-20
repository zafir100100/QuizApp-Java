package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Question;
import services.JsonFileService;
import services.QuestionService;

public class QuestionRepository implements QuestionService {

    private final JsonFileService jfs;

    public QuestionRepository() {
        jfs = new JsonFileRepository("resources/databases/questions.json");
    }

    @Override
    public boolean addQuestion(Question question) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("question", question.question);
            map.put("optionA", question.optionA);
            map.put("optionB", question.optionB);
            map.put("optionC", question.optionC);
            map.put("optionD", question.optionD);
            map.put("answer", question.answer);
            boolean isAdded = jfs.createItemsInJsonFile(map);
            if (isAdded == false) {
                System.out.println("Question Addition Failed");
            }
            return true;
        } catch (Exception ex) {
            System.out.println("Question Addition Failed. Exception occured");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Map<String, String>> getRandomQuestions(int numberOfQuestions) {
        try {
            List<String> keys = new ArrayList<>();
            keys.add("question");
            keys.add("optionA");
            keys.add("optionB");
            keys.add("optionC");
            keys.add("optionD");
            keys.add("answer");
            List<Map<String, String>> questions = jfs.getRandomRows(keys, numberOfQuestions);
            if (!questions.isEmpty()) {
                return questions;
            }
            return null;
        } catch (Exception ex) {
            System.out.println("Random Question Retrival Failed. Exception occured");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
