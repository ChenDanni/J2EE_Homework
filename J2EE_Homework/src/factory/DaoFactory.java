package factory;

import dao.ExamDao;
import dao.impl.ExamDaoImpl;

public class DaoFactory {
	
	public static ExamDao getExamDao(){
		return ExamDaoImpl.getInstance();
	}
	
}
