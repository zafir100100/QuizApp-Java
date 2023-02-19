package services;

import java.util.List;
import java.util.Map;

public interface JsonFileService {
    public boolean createItemsInJsonFile(Map<String, String> map);
    public List<Map<String, String>> getAllRow(List<String> keys);
}
