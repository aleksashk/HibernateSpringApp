package ru.philimonov.hibernatecourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.hibernatecourse.model.Item;
import ru.philimonov.hibernatecourse.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = new Person("Nickola", 22);
            Item newItem = new Item("Test Item", person);
            person.setItems(new ArrayList<Item>(Collections.singletonList(newItem)));
            session.save(person);
            session.save(newItem);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
