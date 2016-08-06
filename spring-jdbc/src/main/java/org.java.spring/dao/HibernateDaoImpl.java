package org.java.spring.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //Mark this dao as Spring bean so we can use it directly in SJDBCDemo.java without declaring in Xml
public class HibernateDaoImpl {

	/* In hibernate tutorials we buildSessionFactory based on configuration defined in hibernate.cfg.xml but here we defined the configuration in spring .xml; we need sessionFactory object (which is expensive, shared & singelton) to access session; spring.XML initiates the sessionFactory (same as doing new SessionFactory(), ensures singelton) and @Autowiring wires this class’s sessionFactory object to one initiated in spring.xml  */
	@Autowired // injects AnnotationSessionFactoryBean in this Dao (Autowired put id='sessionFactory' in xml)
	private SessionFactory sessionFactory;


	public int getCircleCount(){
		String hql = "select count(*) from Circle";
		Query query = getSessionFactory().openSession().createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}}

