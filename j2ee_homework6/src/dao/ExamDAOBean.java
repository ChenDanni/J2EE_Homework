package dao;

import dao.impl.DaoHelperImpl;
import model.Exam;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    @PersistenceContext
    protected EntityManager em;

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

        List<Exam> exams = new ArrayList<>();
        try {
            Query query = em.createQuery("select e from Exam e, User u " +
                    "where e.student_id = u.id and u.username = '" + username + "'");
            exams = query.getResultList();
            return exams;
        }catch(Exception e){
            e.printStackTrace();
        }

        return exams;
    }

    @Override
    public void updateById(Exam exam) {

    }

    @Override
    public List find() {
        return null;
    }
}
