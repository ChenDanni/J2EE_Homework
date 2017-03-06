package service;

import action.business.ExamInfoVO;
import dao.ExamDao;
import model.Exam;
import utility.PageState;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
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
        List<ExamInfoVO> examInfoVOs = new ArrayList<>();
        List<Exam> exams = examDao.find(username);
        for (int i = 0;i < exams.size();i++){
            ExamInfoVO vo = new ExamInfoVO();
            Exam e = exams.get(i);
            vo.setLesson(e.getLesson());
            vo.setScore(e.getScore());
            vo.setState(getTestState(e.getState()));
            vo.setTest_name(e.getTest_name());
            examInfoVOs.add(vo);
        }
        return examInfoVOs;
    }

    @Override
    public PageState getPageState(String username) {
        PageState pageState = PageState.COMMON;
        List<ExamInfoVO> exams = getExamsInfo(username);
        for (int i = 0;i < exams.size();i++){
            ExamInfoVO t = exams.get(i);
            if (t.getState().equals("未完成")){
                pageState = PageState.WARNING;
                break;
            }
        }
        return pageState;
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
