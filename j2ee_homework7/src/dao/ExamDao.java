package dao;


import java.util.List;
import model.*;

public interface ExamDao {

	public void save(Exam exam);
	
	public List find(String colum, String value);
	
	public List find(String name);
	
	public void updateById(Exam exam);
	
	public List find();
	
}
