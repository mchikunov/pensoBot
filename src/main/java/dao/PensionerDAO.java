package dao;

import model.Pensioner;
import org.hibernate.HibernateException;

import java.awt.*;
import java.util.Collection;

public interface PensionerDAO {
    Pensioner getPensioner(long id) throws HibernateException;
    void addPensioner(Pensioner pensioner) throws HibernateException;
    void deletePensioner(Pensioner pensioner) throws HibernateException;
    Pensioner getPensionerByPhone(String phoneNumber) throws HibernateException;

    void updatePensioner(Pensioner pensioner) throws HibernateException;
}
