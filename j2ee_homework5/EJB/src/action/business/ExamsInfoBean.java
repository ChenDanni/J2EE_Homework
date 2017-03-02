package action.business;

import java.util.ArrayList;
import java.util.List;

import model.Exam;

public class ExamsInfoBean {

	private List examsInfoList;

	public List getExamsInfoList(){
		return examsInfoList;
	}

	public Exam getExamsInfoList(int index){
		return (Exam)examsInfoList.get(index);
	}
	
	public void setExamsInfoList(List examsInfoList) {
		this.examsInfoList = examsInfoList;
	}
	
	
}
