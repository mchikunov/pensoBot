package service;

import model.Pensioner;
import org.hibernate.HibernateException;

public interface PensionerService {
    Pensioner getPensioner(long id) throws HibernateException;
    void addPensioner(Pensioner pensioner) throws HibernateException;
    Pensioner getPensionerByPhone(String phoneNumber) throws HibernateException;
    void deletePensioner(Pensioner pensioner) throws HibernateException;
    void updatePensioner(Pensioner pensioner) throws HibernateException;

}
