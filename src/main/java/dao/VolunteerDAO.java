package dao;

import model.Volunteer;
import org.hibernate.HibernateException;

import java.awt.*;
import java.util.Collection;
import java.util.List;

public interface VolunteerDAO {

    Volunteer getVolunteer(long id) throws HibernateException;

    long addVolunteer(Volunteer volunteer) throws HibernateException;

    void updateVolunteer(Volunteer volunteer) throws HibernateException;

    void updateVolunteer(long chatId, boolean status) throws HibernateException;

    List getAllVolunteers() throws HeadlessException;

    void deleteVolunteer(Volunteer volunteer) throws HibernateException;

    List getAllFreeVolunteers() throws HeadlessException;

    Volunteer getVolunteerByChatId(long chatId) throws HeadlessException;

    void setStatus(boolean status);

}
