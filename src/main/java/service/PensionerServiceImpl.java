package service;

import dao.PensionerDAO;
import dao.PensionerDAOImpl;
import model.Pensioner;
import org.hibernate.HibernateException;

public class PensionerServiceImpl implements PensionerService {

    private static final PensionerDAO pensionerDAO = new PensionerDAOImpl();

    @Override
    public Pensioner get(long id) throws HibernateException {
        return pensionerDAO.getPensioner(id);
    }

    @Override
    public long addPensioner(Pensioner pensioner) throws HibernateException {
        return 0;
    }

}
