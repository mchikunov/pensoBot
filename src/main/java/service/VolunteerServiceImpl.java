package service;

import dao.VolunteerDAO;
import dao.VolunteerDAOImpl;
import model.Volunteer;
import org.hibernate.HibernateException;

import java.awt.*;
import java.util.Collection;

public class VolunteerServiceImpl implements VolunteerService {

    private static final VolunteerDAO volunteerDAO = new VolunteerDAOImpl();


    @Override
    public Volunteer getVolunteer(long id) throws HibernateException {
        return volunteerDAO.getVolunteer(id);
    }

    @Override
    public long addVolunteer(Volunteer volunteer) throws HibernateException {
        return volunteerDAO.addVolunteer(volunteer);
    }

    @Override
    public void updateVolunteer(Volunteer volunteer) throws HibernateException {
        volunteerDAO.updateVolunteer(volunteer);
    }

    @Override
    public Collection getAllVolunteers() throws HeadlessException {
        return volunteerDAO.getAllVolunteers();
    }

    @Override
    public long addVolonter(Volunteer volunteer) throws HibernateException {
        volunteerDAO.addVolunteer(volunteer);
        return volunteerDAO.getID(volunteer);
    }

    @Override
    public Collection getAllFreeVolunteers() throws HeadlessException {
        return volunteerDAO.getAllFreeVolunteers();
    }

    @Override
    public Volunteer getVolunteerByChatId(long chatId) throws HeadlessException {
        return volunteerDAO.getVolunteerByChatId(chatId);
    }
}
