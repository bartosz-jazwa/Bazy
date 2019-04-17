package hibernate.dao;

import hibernate.entity.Run;
import hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class RunDaoImpl implements RunDao {
    @Override
    public void save(Run run) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(run);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete Run where id=:id").setParameter("id", id).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Run getRun(Integer id) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            Run run = (Run) session.createQuery("from Run where id=:id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
            session.close();
            return run;
        } catch (NoResultException e) {
            session.getTransaction().rollback();
            System.out.println("no result for " + id);
        }
        session.close();
        return null;
    }

    @Override
    public List<Run> getAll() {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<Run> runs = session.createQuery("from Run").list();
        session.getTransaction().commit();
        session.close();
        return runs;

    }
}
