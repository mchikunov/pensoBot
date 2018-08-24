package dao;

import model.Volunteer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.DbHelper;

import java.awt.*;
import java.util.Collection;


public class VolunteerDAOImpl implements VolunteerDAO {

    private Session session;

    public VolunteerDAOImpl() {
        session = DbHelper.getSessionFactory().openSession();
    }

    @Override
    public VolunteerDAO getVolunteer(long id) throws HibernateException {
        return null;
    }

    @Override
    public long addVolunteer(Volunteer volunteer) throws HibernateException {
        return 0;
    }

    @Override
    public void upDateVolunteer(Volunteer volunteer) throws HibernateException {

    }

    @Override
    public Collection getAllVolunteer() throws HeadlessException {
        return null;
    }

    @Override
    public void delateVolunteer(Volunteer volunteer) throws HibernateException {

    }

    @Override
    public Volunteer getVolunteerByStatus(boolean status) throws HibernateException {
        return null;
    }

    @Override
    public String getVolunteerByRank(String rank) throws HibernateException {
        return null;
    }
}
