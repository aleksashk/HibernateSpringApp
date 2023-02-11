package ru.philimonov.hibernatecourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.hibernatecourse.model.Passport;
import ru.philimonov.hibernatecourse.model.Person;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = new Person("Test person", 35);
            Passport passport = new Passport(person, 14578);
            person.setPassport(passport);
            session.save(person);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
