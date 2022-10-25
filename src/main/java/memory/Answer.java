package memory;

import org.json.JSONObject;

public class Answer {
    String questionId;
    boolean correctness;
    String confidence;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public boolean isCorrectness() {
        return correctness;
    }

    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public Answer(String questionId, boolean correctness, String confidence) {
        this.questionId = questionId;
        this.correctness = correctness;
        this.confidence = confidence;
    }

    public Answer(JSONObject o) {
        this.questionId = (String) o.get("questionId");
        this.correctness = (boolean) o.get("correctness");
        this.confidence = (String) o.get("confidence");
    }

    public JSONObject toJSON() {
        JSONObject res = new JSONObject();
        res.put("questionId", this.questionId);
        res.put("correctness", this.correctness);
        res.put("confidence", this.confidence);
        return res;
    }
}
