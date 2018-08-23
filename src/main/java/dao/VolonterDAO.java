package dao;

import model.User;
import model.Volonter;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class VolonterDAO {

    private Session session;

    public VolonterDAO(Session session) {
        this.session = session;
    }

    public Volonter get(long id) throws HibernateException {
        return (Volonter) session.get(User.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(Volonter.class);
        return ((Volonter) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

//    public long insertUser(String name) throws HibernateException {
//        return (Long) session.save(new Volonter(name));
//    }

}
