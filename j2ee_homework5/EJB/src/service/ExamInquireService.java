package service;

import java.util.List;
import utility.PageState;

import javax.ejb.Remote;

@Remote
public interface ExamInquireService {
	
	public List getExamsInfo(String username);
	
	public PageState getPageState(String username);
	
}
