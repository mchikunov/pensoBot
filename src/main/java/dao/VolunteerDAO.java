package dao;

import model.Volunteer;
import org.hibernate.HibernateException;

import java.awt.*;
import java.util.Collection;

public interface VolunteerDAO {

    Volunteer getVolunteer(long id) throws HibernateException;
    public long getID(Volunteer volunteer) throws  HibernateException;

    public void addVolunteer(Volunteer volunteer) throws HibernateException;

    public void upDateVolunteer(Volunteer volunteer) throws HibernateException;
    public Collection getAllVolunteers() throws HeadlessException;
    public Collection getAllFreeVolunteers() throws HeadlessException;
    public void delateVolunteer(Volunteer volunteer) throws HibernateException;

    public Volunteer getVolunteerByStatus(boolean status) throws HibernateException;
    public String getVolunteerByRank (String rank) throws HibernateException;

}
