package service;

import model.Pensioner;
import org.hibernate.HibernateException;

public interface PensionerService {

    Pensioner getPensioner(long id) throws HibernateException;

    long addPensioner(Pensioner pensioner) throws HibernateException;
    public Pensioner getPensionerByPhone(String phoneNumber) throws HibernateException;
}
