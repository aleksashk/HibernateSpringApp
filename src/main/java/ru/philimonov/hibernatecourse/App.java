package ru.philimonov.hibernatecourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.hibernatecourse.model.Person;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            List<Person> people = session.createQuery("from Person where age>30 and name like 'D%'" ).getResultList();
            for (Person person : people) {
                System.out.println(person);
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
