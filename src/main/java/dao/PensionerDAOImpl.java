package dao;

import model.Pensioner;
import model.Volunteer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.DbHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class PensionerDAOImpl implements PensionerDAO {

    @Override
    public Pensioner getPensioner(long id) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        Pensioner pensioner = (Pensioner) session.createQuery("from Pensioner where id = " + id).uniqueResult();
        session.close();
        return pensioner;
    }

    @Override
    public void addPensioner(Pensioner pensioner) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(pensioner);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
    }

    @Override
    public void deletePensioner(Pensioner pensioner) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.delete(pensioner);
            session.getTransaction().commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
            session.close();
        }
    }

    @Override
    public Pensioner getPensionerByPhone(String phoneNumber) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Pensioner where phone =:phoneNumber").setString("phoneNumber",phoneNumber);

        List <Pensioner> pensioner = query.list();

        session.close();
        return pensioner.size() == 0? null : pensioner.get(0) ;
    }

    @Override
    public void updatePensioner(Pensioner pensioner) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(pensioner);
        session.getTransaction().commit();
        session.close();
    }
}
