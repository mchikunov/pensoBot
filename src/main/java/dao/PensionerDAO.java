package dao;

import model.Pensioner;
import org.hibernate.HibernateException;

import java.awt.*;
import java.util.Collection;

public interface PensionerDAO {

    public Pensioner getPensioner(long id) throws HibernateException;
    public long getID(Pensioner pensioner) throws  HibernateException;

    public void addPensioner(Pensioner pensioner) throws HibernateException;
    public void upDatePensioner(Pensioner pensioner) throws HibernateException;
    public Collection getAllPensioners() throws HeadlessException;
    public void delatePensioner(Pensioner pensioner) throws HibernateException;

    public Pensioner getPensionerByPhone(String phoneNumber) throws HibernateException;
    public String getAddressByPhone (String phoneNumber) throws HibernateException;

}
