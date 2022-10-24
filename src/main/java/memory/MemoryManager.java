package memory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



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
                memory.put(k ,tmp);
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
                for (Answer a: memory.get(k)) {
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
}
