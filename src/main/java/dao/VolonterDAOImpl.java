package dao;

import model.Volonter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.DbHelper;

import java.awt.*;
import java.util.Collection;


public class VolonterDAOImpl implements VolonterDAO {

    private Session session;

    public VolonterDAOImpl() {
        session = DbHelper.getSessionFactory().openSession();
    }

    @Override
    public VolonterDAO getVolonter(long id) throws HibernateException {
        return null;
    }

    @Override
    public long addVolonter(Volonter volonter) throws HibernateException {
        return 0;
    }

    @Override
    public void upDateVolonter(Volonter volonter) throws HibernateException {

    }

    @Override
    public Collection getAllVolonter() throws HeadlessException {
        return null;
    }

    @Override
    public void delateVolonter(Volonter volonter) throws HibernateException {

    }

    @Override
    public Volonter getVolonterByStatus(boolean status) throws HibernateException {
        return null;
    }

    @Override
    public String getVolonterByRank(String rank) throws HibernateException {
        return null;
    }
}
