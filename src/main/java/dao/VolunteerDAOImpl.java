package dao;


import model.Pensioner;
import model.Volunteer;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.DbHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class VolunteerDAOImpl implements VolunteerDAO {

    private Session session;

    public VolunteerDAOImpl() {
        session = DbHelper.getSessionFactory().openSession();
    }

    @Override
    public Volunteer getVolunteer(long id) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        Volunteer volunteer = (Volunteer) session.load(Volunteer.class, id);
        session.close();
        return volunteer;
    }

    @Override
    public void addVolunteer(Volunteer volunteer) throws HibernateException {
       Session session = DbHelper.getSessionFactory().openSession();
       session.beginTransaction();
       session.save(volunteer);
       session.getTransaction().commit();
       session.close();
    }

    @Override
    public void upDateVolunteer(Volunteer volunteer) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(volunteer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Collection getAllVolunteers() throws HeadlessException {
        Session session = DbHelper.getSessionFactory().openSession();
        List volunteers = new ArrayList<Volunteer>();
        volunteers = session.createCriteria(Volunteer.class).list();
        session.close();
        return volunteers;
    }

    @Override
    public void delateVolunteer(Volunteer volunteer) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(volunteer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public long getID(Volunteer volunteer) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        long id = (long)session.load(Pensioner.class, volunteer.getId());
        session.close();
        return id;
    }

    @Override
    public Collection getAllFreeVolunteers() throws HeadlessException {
        Session session = DbHelper.getSessionFactory().openSession();
        List volunteers = new ArrayList<Volunteer>();
        volunteers = session.createQuery("from Volunteer where status = true").list();
        session.close();
        return volunteers;
    }

    @Override
    public Volunteer getVolunteerByStatus(boolean status) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        Volunteer volunteer = (Volunteer)session.createQuery("from Volunteer where status = true").setMaxResults(1);
        session.close();
        return volunteer;
    }

    @Override
    public String getVolunteerByRank(String rank) throws HibernateException {
        return null;
    }
}
