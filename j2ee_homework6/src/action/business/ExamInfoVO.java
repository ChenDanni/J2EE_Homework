package action.business;

import java.io.Serializable;

/**
 * Created by chendanni on 17/2/24.
 */
public class ExamInfoVO implements Serializable{

    private String test_name;
    private String state;
    private int score;
    private String lesson;

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
