package service;

import model.Volunteer;
import org.hibernate.HibernateException;

import java.awt.*;
import java.util.*;
import java.util.List;

public interface VolunteerService {

    Volunteer getVolunteer(long id) throws HibernateException;

    long addVolunteer(Volunteer volunteer) throws HibernateException;

    void updateVolunteer(Volunteer volunteer) throws HibernateException;

    Collection getAllVolunteers() throws HeadlessException;

    void deleteVolunteer(Volunteer volunteer) throws HibernateException;

    List getAllFreeVolunteers() throws HeadlessException;

    Volunteer getVolunteerByChatId(long chatId) throws HeadlessException;


}
