package hh.hw.javadb.employers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmployerService implements EmployerDAO {

    private final SessionFactory sessionFactory;

    public EmployerService(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addEmployer(Employer employer) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(employer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateEmployer(Employer employer) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.update(employer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Employer getEmployerById(Long id) throws SQLException {
        Session session = null;
        Employer stud = null;
        try {
            session = sessionFactory.openSession();
            stud = (Employer) session.load(Employer.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stud;
    }

    @Override
    public List getAllEmployers() throws SQLException {
        Session session = null;
        List<Employer> empls = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            empls = session.createCriteria(Employer.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return empls;
    }

    @Override
    public void deleteEmployer(Employer employer) throws SQLException {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.delete(employer);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
