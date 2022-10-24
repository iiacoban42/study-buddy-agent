package memory;

import org.json.JSONObject;

public class Answer {
    int questionId;
    boolean correctness;
    double confidence;

    public Answer(int questionId, boolean correctness, float confidence) {
        this.questionId = questionId;
        this.correctness = correctness;
        this.confidence = confidence;
    }

    public Answer(JSONObject o) {
        this.questionId = (int) o.get("questionId");
        this.correctness = (boolean) o.get("correctness");
        this.confidence = (double) o.get("confidence");
    }

    public JSONObject toJSON() {
        JSONObject res = new JSONObject();
        res.put("questionId", this.questionId);
        res.put("correctness", this.correctness);
        res.put("confidence", this.confidence);
        return res;
    }
}
