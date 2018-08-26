package dao;

import model.Pensioner;
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
    public long getID(Pensioner pensioner) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        long id = (long)session.load(Pensioner.class, pensioner.getId());
        session.close();
        return id;
    }

    @Override
    public Pensioner getPensioner(long id) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        Pensioner pensioner = (Pensioner) session.load(Pensioner.class, id);
        session.close();
        return pensioner;
    }

    @Override
    public void addPensioner(Pensioner pensioner) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(pensioner);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void upDatePensioner(Pensioner pensioner) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(pensioner);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Collection getAllPensioners() throws HeadlessException {
        Session session = DbHelper.getSessionFactory().openSession();
        List pensioners = new ArrayList<Pensioner>();
        pensioners = session.createCriteria(Pensioner.class).list();
        session.close();
        return pensioners;
    }

    @Override
    public void delatePensioner(Pensioner pensioner) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(pensioner);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Pensioner getPensionerByPhone(String phoneNumber) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Pensioner where phone =:phoneNumber").setString("phoneNumber",phoneNumber);

        List <Pensioner> pensioner = query.list();


        //pensioner.add(new Pensioner("ivan", "ivanov", "105", "lenina", "123132", "durak"));

        session.close();
        return pensioner.size() == 0? null : pensioner.get(0) ;
    }

    @Override
    public String getAddressByPhone(String phoneNumber) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        Pensioner pensioner = (Pensioner)session.load(String.class, phoneNumber);
        String address = pensioner.getAddress();
        session.close();
        return address;
    }
}
