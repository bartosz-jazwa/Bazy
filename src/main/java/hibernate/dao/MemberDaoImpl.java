package hibernate.dao;

import hibernate.entity.Member;
import hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class MemberDaoImpl implements MemberDao {

    SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
    Session session = factory.getCurrentSession();
    @Override
    public void save(Member member) {

        session.beginTransaction();
        session.saveOrUpdate(member);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete Member where id=:id").setParameter("id", id).executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Member getMember(Integer id) {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            Member member = (Member) session.createQuery("from Member where id=:id").setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
            session.close();
            return member;
        } catch (NoResultException e) {
            session.getTransaction().rollback();
            System.out.println("no result for " + id);
        }
        session.close();
        return null;
    }

    @Override
    public List<Member> getAll() {
        SessionFactory factory = HibernateUtils.getInstance().getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Member> members = session.createQuery("from Member").list();
        session.getTransaction().commit();
        session.close();
        return members;
    }
}
