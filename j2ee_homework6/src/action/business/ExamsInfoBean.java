package action.business;

import java.util.ArrayList;
import java.util.List;

import model.Exam;

public class ExamsInfoBean {

	private List examsInfoList;

	public List getExamsInfoList(){
		return examsInfoList;
	}

	public ExamInfoVO getExamsInfoList(int index){
		return (ExamInfoVO) examsInfoList.get(index);
	}
	
	public void setExamsInfoList(List examsInfoList) {
		this.examsInfoList = examsInfoList;
	}

}
