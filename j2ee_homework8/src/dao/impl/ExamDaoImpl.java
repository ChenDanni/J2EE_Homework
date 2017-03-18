package dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import action.business.ExamInfoVO;
import dao.*;
import model.Exam;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import utility.TestState;

import javax.persistence.Query;
@Repository
@Transactional
public class ExamDaoImpl implements ExamDao{

	@Autowired
	private BaseDAO baseDAO;

	private ExamDaoImpl() {}

	
	@Override
	public void save(Exam exam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List find(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String name) {

//		List res = new ArrayList();
		Session session = baseDAO.getNewSession();
		String hql = "SELECT ex FROM Exam ex, User u " +
				" WHERE ex.student_id = u.id and u.username = '" + name + "'";
		return session.createQuery(hql).list();

	}

	@Override
	public void updateById(Exam exam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List find() {
		// TODO Auto-generated method stub
		return null;
	}


}
