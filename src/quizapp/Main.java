/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quizapp;

import java.util.HashMap;
import repositories.JsonFileRepository;

/**
 *
 * @author x
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Mango");  //Put elements in Map  
        map.put("2", "Apple");
        map.put("3", "Banana");
        map.put("4", "Grapes");
        JsonFileRepository j = new JsonFileRepository("resources/databases/questions.json");
        j.createItemsInJsonFile(map);

    }

}
