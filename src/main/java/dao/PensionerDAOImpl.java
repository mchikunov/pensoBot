package dao;

import model.Pensioner;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class PensionerDAOImpl implements PensionerDAO {

    private Session session;

    public PensionerDAOImpl() {
        session = DbHelper.getSessionFactory().openSession();
    }

    @Override
    public Pensioner get(long id) throws HibernateException {
        return null;
    }

    @Override
    public long addPensioner(Pensioner pensioner) throws HibernateException {
        return 0;
    }
}
