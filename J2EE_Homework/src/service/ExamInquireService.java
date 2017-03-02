package service;

import java.util.List;
import utility.PageState;

public interface ExamInquireService {
	
	public List getExamsInfo(String name);
	
	public PageState getPageState(String name);
	
}
