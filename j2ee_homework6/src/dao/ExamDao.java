package dao;


import java.util.List;
import model.*;

import javax.ejb.Remote;

@Remote
public interface ExamDao {

	public void save(Exam exam);
	
	public List find(String colum, String value);
	
	public List find(String username);
	
	public void updateById(Exam exam);
	
	public List find();
	
}
