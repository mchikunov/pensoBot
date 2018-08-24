package service;

import model.Volonter;
import org.hibernate.HibernateException;

public interface VolonterService {

    Volonter getVolonter(long id) throws HibernateException;

    long addVolonter(Volonter volonter) throws HibernateException;
}
