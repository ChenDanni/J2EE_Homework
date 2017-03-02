package service;

import dao.ExamDao;
import model.Exam;
import utility.PageState;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by user on 17/2/22.
 */
@Stateless(name = "ExamInquireServiceEJB")
public class ExamInquireServiceBean implements ExamInquireService{
    @EJB ExamDao examDao;

    public ExamInquireServiceBean() {}

    @Override
    public List getExamsInfo(String username) {
        return examDao.find(username);
    }

    @Override
    public PageState getPageState(String username) {
        PageState pageState = PageState.COMMON;
        List<Exam> exams = getExamsInfo(username);
        for (int i = 0;i < exams.size();i++){
            Exam t = exams.get(i);
            if (t.getState().equals("未完成")){
                pageState = PageState.WARNING;
                break;
            }
        }
        return pageState;
    }
}
