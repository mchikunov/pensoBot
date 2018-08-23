//package service;
//
//import dao.UsersDAO;
//import model.Pensioner;
//import model.User;
//import model.Volonter;
//import org.hibernate.*;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.internal.SessionFactoryImpl;
//import org.hibernate.service.ServiceRegistry;
//
//import java.sql.*;
//
//public class Service {
//
//    public User getUser(long id) throws DBException {
//        try {
//            Session session = sessionFactory.openSession();
//            UsersDAO dao = new UsersDAO(session);
//            User dataSet = dao.get(id);
//            session.close();
//            return dataSet;
//        } catch (HibernateException e) {
//            throw new DBException(e);
//        }
//    }
//
//    public Object getUser(String name) throws DBException {
//
//        Session session = sessionFactory.openSession();
//
//       // Query query = session.createQuery("FROM User WHERE name = :paramName");
//       // query.setParameter("paramName", name);
//
//        try {
//            return 1;
//            //return query.uniqueResult();
//        } catch (HibernateException e) {
//            throw new DBException(e);
//        }
//    }
//
//    public long addUser(String name) throws DBException {
//        try {
//            Session session = sessionFactory.openSession();
//            Transaction transaction = session.beginTransaction();
//            UsersDAO dao = new UsersDAO(session);
//            long id = dao.insertUser(name);
//            transaction.commit();
//            session.close();
//            return id;
//        } catch (HibernateException e) {
//            throw new DBException(e);
//        }
//    }
//
//
//    public void printConnectInfo() {
//        try {
//            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
//            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
//            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
//            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
//            System.out.println("Driver: " + connection.getMetaData().getDriverName());
//            System.out.println("Autocommit: " + connection.getAutoCommit());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
