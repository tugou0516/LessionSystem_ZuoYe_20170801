package org.forten.zuoye.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateDao {
	// SessionFactory等同于JDBC中的DataSource
	private static final SessionFactory FACTORY = buildSessionFactory();

	// 创建SessionFactory
	private static SessionFactory buildSessionFactory() {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			return new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return FACTORY;
	}

	// 获取Session（等同于JDBC中的Connection）
	public static Session getSession() {
		return FACTORY.openSession();
	}

	public static void close(Session session) {
		if (session != null) {
			session.close();
		}
	}
}
