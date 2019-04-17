package hibernate.dao;

import hibernate.entity.Chip;
import hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class ChipDaoImpl implements ChipDao {
    @Override
    public void save(Chip chip) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(chip);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete Chip where id=:id").setParameter("id", id).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Chip getChip(Integer id) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            Chip chip = (Chip) session.createQuery("from Chip where id=:id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
            session.close();
            return chip;
        } catch (NoResultException e) {
            session.getTransaction().rollback();
            System.out.println("no result for " + id);
        }
        session.close();
        return null;
    }

    @Override
    public List<Chip> getAll() {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<Chip> chips = session.createQuery("from Chip").list();
        session.getTransaction().commit();
        session.close();
        return chips;
    }
}
