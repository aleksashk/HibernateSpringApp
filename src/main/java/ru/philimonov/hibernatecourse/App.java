package ru.philimonov.hibernatecourse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.philimonov.hibernatecourse.model.Actor;
import ru.philimonov.hibernatecourse.model.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try (sessionFactory) {
            session.beginTransaction();

            Movie movie = new Movie("Reservoir Dogs", 1992);
            Actor actor = session.get(Actor.class, 1);
            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));

            actor.getMovies().add(movie);
            session.save(movie);

            session.getTransaction().commit();
        }

    }
}
