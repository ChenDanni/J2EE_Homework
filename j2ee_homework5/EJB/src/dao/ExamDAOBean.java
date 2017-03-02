package dao;

import dao.impl.DaoHelperImpl;
import model.Exam;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17/2/22.
 */
@Stateless(name = "ExamDAOEJB")
public class ExamDAOBean implements ExamDao{
    private static DaoHelper daoHelper = DaoHelperImpl.getDaoHelper();

    public ExamDAOBean() {}

    @Override
    public void save(Exam exam) {

    }

    @Override
    public List find(String colum, String value) {
        return null;
    }

    @Override
    public List find(String username) {
        Connection connection = daoHelper.getConnection();
        ArrayList list = new ArrayList<>();

        try{
            String sql = "SELECT * FROM student JOIN test ON student.id = test.student_id WHERE username = '" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                String test_name = resultSet.getString("test_name");
                String state = resultSet.getString("state");
                int score = resultSet.getInt("score");
                String lesson = resultSet.getString("lesson");
                String s = getTestState(state);

                Exam examInfo = new Exam();
                examInfo.setState(s);
                examInfo.setName(test_name);
                examInfo.setScore(score);
                examInfo.setStudent_name(username);
                examInfo.setLesson(lesson);
                list.add(examInfo);
            }


        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void updateById(Exam exam) {

    }

    @Override
    public List find() {
        return null;
    }

    public String getTestState(String state){
        switch (state) {
            case "wait":
                return "未完成";
            case "finish":
                return "已结束";
            default:
                return "error";
        }
    }
}
