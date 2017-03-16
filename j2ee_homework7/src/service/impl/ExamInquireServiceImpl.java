package service.impl;


import java.util.ArrayList;
import java.util.List;

import action.business.ExamInfoVO;
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
		List<Exam> exams = DaoFactory.getExamDao().find(name);
		List<ExamInfoVO> examInfoVOs = new ArrayList<>();
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
	public PageState getPageState(String name) {
		PageState pageState = PageState.COMMON;
		List<ExamInfoVO> exams = getExamsInfo(name);
		for (int i = 0;i < exams.size();i++){
			ExamInfoVO t = exams.get(i);
			if (t.getState().equals("未完成")){
				pageState = PageState.WARNING;
				break;
			}
		}
		return pageState;
	}

	public static String getTestState(String state){
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
