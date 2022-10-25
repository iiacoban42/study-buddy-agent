package memory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class MemoryManager {
    Map<String, ArrayList<Answer>> memory;

    public MemoryManager(){
        memory = new HashMap<>();
    }

    public void load(String filePath) {
        try {
            String str = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject data = new JSONObject(str);
            for (Iterator<String> it = data.keys(); it.hasNext(); ) {
                String k = it.next();
                ArrayList<Answer> tmp = new ArrayList<>();
                for(Object o: (JSONArray) data.get(k)) {
                    tmp.add(new Answer((JSONObject) o));
                }
                // remove possible duplicates
                Set<Answer> s = new LinkedHashSet<>(tmp);

                ArrayList<Answer> a = new ArrayList<>(s);

                memory.put(k ,a);
            }
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public void store(String filePath) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            JSONObject res = new JSONObject();
            for (String k : memory.keySet()) {
                JSONArray arr = new JSONArray();
                Set<Answer> s = new LinkedHashSet<>();
                for (Answer a: memory.get(k)) {
                    if(s.add(a))
                        arr.put(a.toJSON());
                }
                res.put(k ,arr);
            }
            myWriter.write(res.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public void addToPerson(String name, Answer a){
        if (!memory.containsKey(name) || memory.get(name) == null) {
            memory.put(name, new ArrayList<>());
        }
        memory.get(name).add(a);
    }

    public ArrayList<Answer> getPersonMemory(String name){
        if (!memory.containsKey(name) || memory.get(name) == null) {
            return new ArrayList<>();
        }
        return memory.get(name);
    }

    public boolean memoryContainsQuestion(String name, String question_id){
        ArrayList<Answer> answers = getPersonMemory(name);

        boolean found = false;

        if(!answers.isEmpty()){
            for(Answer a : answers)
                if(a.getQuestionId().equals(question_id))
                    found = true;
        }

        return found;
    }

    public int getPersonScore(String name){
        ArrayList<Answer> answers = getPersonMemory(name);

        int score = 0;

        for(Answer a : answers){
            if(a.correctness == true)
                score++;
        }
        return score;
    }

    public String getPersonTopic(String name){
        ArrayList<Answer> answers = getPersonMemory(name);

        Answer a = answers.get(0);

        String topic = a.getQuestionId();

        System.out.println(topic);

        if(topic.contains("Black_holes"))
            return "blackHoles";
        else if (topic.contains("Space_exploration")) {
            return "spaceExploration";
        } else if (topic.contains("Solar_system")) {
            return "solarSystem";
        }
        return null;
    }

    public String getPersonConfidence(String name, String question_id){
        ArrayList<Answer> answers = getPersonMemory(name);

        String confidence = "";

        if(memoryContainsQuestion(name, question_id)){
            if(!answers.isEmpty()){
                for(Answer a : answers)
                    if(a.getQuestionId().equals(question_id))
                        confidence = a.getConfidence();
            }
        }

        return confidence;
    }

    public boolean getQuestionCorrectness(String name, String question_id){
        ArrayList<Answer> answers = getPersonMemory(name);

        boolean correctness = false;

        if(memoryContainsQuestion(name, question_id)) {
            if (!answers.isEmpty()) {
                for (Answer a : answers)
                    if (a.getQuestionId().equals(question_id))
                        correctness = a.isCorrectness();
            }
        }

        return correctness;
    }

    public static void main(String[] args) {
        String path = Paths.get("").toAbsolutePath() + "\\src\\main\\kotlin\\furhatos\\app\\quiz\\db.json";

        MemoryManager mem = new MemoryManager();

        mem.load(path);

        mem.addToPerson("Jack", new Answer("Space_exploration_1", true, "high"));

        mem.addToPerson("Jack", new Answer("Space_exploration_2", false, "low"));

        mem.addToPerson("Jack", new Answer("Space_exploration_3", false, "high"));

        mem.addToPerson("James", new Answer("Black_holes_3", true, "high"));

        mem.store(path);

        mem.load(path);

        System.out.println(mem.getPersonScore("Jack"));

        System.out.println(mem.getPersonTopic("James"));
    }
}
