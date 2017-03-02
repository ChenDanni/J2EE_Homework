package service.impl;


import java.util.List;

import action.business.ExamsInfoBean;
import action.business.StatesBean;
import factory.DaoFactory;
import model.Exam;
import service.ExamInquireService;
import utility.PageState;

public class ExamInquireServiceImpl implements ExamInquireService{

	private static ExamInquireServiceImpl examInquireService = new ExamInquireServiceImpl();
	
	private ExamInquireServiceImpl(){}
	
	public static ExamInquireServiceImpl getInstance(){
		return examInquireService;
	}

	@Override
	public List getExamsInfo(String name) {
		return DaoFactory.getExamDao().find(name);
	}

	@Override
	public PageState getPageState(String name) {
		PageState pageState = PageState.COMMON;
		List<Exam> exams = getExamsInfo(name);
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
