package service;

import dao.PensionerDAO;
import dao.PensionerDAOImpl;
import dao.VolonterDAO;
import dao.VolonterDAOImpl;
import model.Pensioner;
import model.Volonter;
import org.hibernate.HibernateException;

public class VolonterServiceImpl implements VolonterService {

    private static final VolonterDAO volonterDAO = new VolonterDAOImpl();

    @Override
    public Volonter getVolonter(long id) throws HibernateException {
        return null;
    }

    @Override
    public long addVolonter(Volonter volonter) throws HibernateException {
        return 0;
    }
}
