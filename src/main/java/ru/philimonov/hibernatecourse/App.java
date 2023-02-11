package ru.philimonov.hibernatecourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.hibernatecourse.model.Item;
import ru.philimonov.hibernatecourse.model.Person;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try (sessionFactory) {
            session.beginTransaction();

            Item item = session.get(Item.class, 1);
            System.out.println("get an item");
            System.out.println(item.getOwner());

        }

    }
}
