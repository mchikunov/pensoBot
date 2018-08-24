package service;

import model.Volunteer;
import org.hibernate.HibernateException;

public interface VolunteerService {

    Volunteer getVolonter(long id) throws HibernateException;

    long addVolonter(Volunteer volunteer) throws HibernateException;
    public Volunteer getVolunteerByStatus(boolean status) throws HibernateException;
}
