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

    @Override
    public Volunteer getVolunteer(long id) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        Volunteer volunteer = (Volunteer) session.load(Volunteer.class, id);
        session.close();
        return volunteer;
    }

    @Override
    public long addVolunteer(Volunteer volunteer) throws HibernateException {
       Session session = DbHelper.getSessionFactory().openSession();
       session.beginTransaction();
       long id = (long) session.save(volunteer);
       session.getTransaction().commit();
       session.close();
       return id;
    }

    @Override
    public void updateVolunteer(Volunteer volunteer) throws HibernateException {

    }

    @Override
    public void setStatus(boolean status) {

    }

    @Override
    public void updateVolunteer(long chatId, boolean status) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        Volunteer volunteer = (Volunteer)session.createSQLQuery("UPDATE  Volunteer set status = " + status + "where chatId = " + chatId);
        session.update(volunteer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Collection getAllVolunteers() throws HeadlessException {
        Session session = DbHelper.getSessionFactory().openSession();
        List volunteers = session.createCriteria(Volunteer.class).list();
        session.close();
        return volunteers;
    }

    @Override
    public void deleteVolunteer(Volunteer volunteer) throws HibernateException {
        Session session = DbHelper.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(volunteer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Collection getAllFreeVolunteers() throws HeadlessException {
        Session session = DbHelper.getSessionFactory().openSession();
        List volunteers = session.createQuery("from Volunteer where status = true").list();
        session.close();
        return volunteers;
    }

    @Override
    public Volunteer getVolunteerByChatId(long chatId) throws HeadlessException {
        Session session = DbHelper.getSessionFactory().openSession();
        Volunteer volunteer = (Volunteer) session.createQuery("from Volunteer where chatId = " + chatId).uniqueResult();
        session.close();
        return volunteer;
    }

}
