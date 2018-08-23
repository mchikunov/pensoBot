package dao;

import model.Pensioner;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class PensionerDAO {

    private Session session;

    public PensionerDAO(Session session) {
        this.session = session;
    }

    public Pensioner get(long id) throws HibernateException {
        return (Pensioner) session.get(Pensioner.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(Pensioner.class);
        return ((Pensioner) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new Pensioner(name));
    }

}
