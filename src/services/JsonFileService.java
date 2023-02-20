package services;

import java.util.List;
import java.util.Map;

public interface JsonFileService {

    public boolean createItemsInJsonFile(Map<String, String> map);

    public List<Map<String, String>> getAllRow(List<String> keys);

    public List<Map<String, String>> getRandomRows(List<String> keys, int numberOfRows);

    public List<Map<String, String>> getAllRowByCondition(List<String> keys, Map<String, String> conditions);

    public boolean updateAllRowByCondition(List<String> keys, Map<String, String> conditions, Map<String, String> updatedValues);
}
