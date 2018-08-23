package dao;

import model.Pensioner;
import model.Volonter;
import org.hibernate.HibernateException;

import java.awt.*;
import java.util.Collection;

public interface VolonterDAO {

    VolonterDAO getVolonter(long id) throws HibernateException;

    long addVolonter(Volonter volonter) throws HibernateException;

    public void upDateVolonter(Volonter volonter) throws HibernateException;
    public Collection getAllVolonter() throws HeadlessException;
    public void delateVolonter(Volonter volonter) throws HibernateException;

    public Volonter getVolonterByStatus(boolean status) throws HibernateException;
    public String getVolonterByRank (String rank) throws HibernateException;

}
