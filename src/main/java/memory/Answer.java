package memory;

import org.json.JSONObject;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return correctness == answer.correctness && Objects.equals(questionId, answer.questionId) && Objects.equals(confidence, answer.confidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, correctness, confidence);
    }
}
