package service;

import model.Pensioner;
import org.hibernate.HibernateException;

public interface PensionerService {

    Pensioner get(long id) throws HibernateException;

    long addPensioner(Pensioner pensioner) throws HibernateException;
}
