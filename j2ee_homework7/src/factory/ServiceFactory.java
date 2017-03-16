package factory;

import service.ExamInquireService;
import service.impl.ExamInquireServiceImpl;

public class ServiceFactory {
	
	public static ExamInquireService getExamInquireService(){
		return ExamInquireServiceImpl.getInstance();
	}

}
