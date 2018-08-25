package dao;

import model.Volunteer;
import org.hibernate.HibernateException;

import java.awt.*;
import java.util.Collection;

public interface VolunteerDAO {

    Volunteer getVolunteer(long id) throws HibernateException;
    long getID(Volunteer volunteer) throws  HibernateException;

    long addVolunteer(Volunteer volunteer) throws HibernateException;

    void upDateVolunteer(Volunteer volunteer) throws HibernateException;
    Collection getAllVolunteers() throws HeadlessException;
    Collection getAllFreeVolunteers() throws HeadlessException;
    void delateVolunteer(Volunteer volunteer) throws HibernateException;

    Volunteer getVolunteerByStatus(boolean status) throws HibernateException;
    String getVolunteerByRank (String rank) throws HibernateException;

}
