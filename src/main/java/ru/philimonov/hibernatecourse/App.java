package ru.philimonov.hibernatecourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.hibernatecourse.model.Item;
import ru.philimonov.hibernatecourse.model.Person;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
            Item newItem = new Item("Item from Hibernate", person);
            person.getItems().add(newItem);
            session.save(newItem);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
