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
import utility.TestState;

import javax.persistence.Query;

public class ExamDaoImpl implements ExamDao{

	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	private Session session;
	private static ExamDaoImpl examDao = new ExamDaoImpl();

	private ExamDaoImpl() {}
	
	public static ExamDaoImpl getInstance(){
		return examDao;
	}
	
	@Override
	public void save(Exam exam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List find(String colum, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String name) {

		List res = new ArrayList();

		try {
			configuration = new Configuration().configure();
			configuration.addAnnotatedClass(Exam.class);
			configuration.addAnnotatedClass(User.class);
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();

			String hql = "SELECT ex FROM Exam ex, User u " +
					" WHERE ex.student_id = u.id and u.username = '" + name + "'";
			Query query = session.createQuery(hql);
			res = query.getResultList();
			tx.commit();
			session.close();
			sessionFactory.close();

			return res;
		}catch (Exception e){
			e.printStackTrace();
		}

		return res;
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
