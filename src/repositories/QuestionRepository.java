package repositories;

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
}
