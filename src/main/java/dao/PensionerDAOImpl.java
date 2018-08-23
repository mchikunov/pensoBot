package dao;

import model.Pensioner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.DbHelper;

import java.awt.*;
import java.util.Collection;


public class PensionerDAOImpl implements PensionerDAO {

    @Override
    public Pensioner getPensioner(long id) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        Pensioner pensioner = (Pensioner) session.load(Pensioner.class, id);
        session.close();

        return null;
    }

    @Override
    public long addPensioner(long id, Pensioner pensioner) throws HibernateException {

        return 0;
    }

    @Override
    public void upDatePensioner(Pensioner pensioner) throws HibernateException {

    }

    @Override
    public Collection getAllPensioners() throws HeadlessException {
        return null;
    }

    @Override
    public void delatePensioner(Pensioner pensioner) throws HibernateException {

    }

    @Override
    public Pensioner getPensionerByPhone(String phoneNumber) throws HibernateException {
        return null;
    }

    @Override
    public String getAddressByPhone(String phoneNumber) throws HibernateException {
        return null;
    }
}
