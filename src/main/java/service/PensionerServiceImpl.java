package service;

import dao.PensionerDAO;
import dao.PensionerDAOImpl;
import model.Pensioner;
import org.hibernate.HibernateException;

public class PensionerServiceImpl implements PensionerService {

    private static final PensionerDAO pensionerDAO = new PensionerDAOImpl();

    @Override
    public Pensioner getPensioner(long id) throws HibernateException {
        return pensionerDAO.getPensioner(id);
    }

    @Override
    public void addPensioner(Pensioner pensioner) throws HibernateException {
        pensionerDAO.addPensioner(pensioner);
    }

    @Override
    public Pensioner getPensionerByPhone(String phoneNumber) throws HibernateException {
        return pensionerDAO.getPensionerByPhone(phoneNumber);
    }

}
