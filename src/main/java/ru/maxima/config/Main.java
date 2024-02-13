package ru.maxima.config;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.maxima.model.Order;
import ru.maxima.model.Person;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).
                addAnnotatedClass(Order.class);

        SessionFactory factory = configuration.buildSessionFactory();


        Session session = factory.getCurrentSession();
        try{
            session.beginTransaction();

            Person person = session.get(Person.class , 5);

            List<Order> orders = person.getOrders();

            orders.forEach(session::remove);

            orders.clear();

            person.getOrders().clear();



            session.getTransaction().commit();
        } finally {
            factory.close();

        }
    }
}
//            Person person = new Person(6L ,"Test" , "mail.ru" , 32 , true);
//            Person person1 = session.get(Person.class , 10L);
//
//            person1.setName("damir123");
//
//            System.out.println(person1.getEmail());
//            System.out.println(person1.getName());
//
//            session.delete(person1);
//
//
//           session.save(person);
//            Person person1 = new Person("Test1" , 55);
//            Person person2 = new Person("Test2" , 66);
//            Person person3 = new Person("Test3" , 77);
//            Person person4 = new Person("Test4" , 88);
//
//            session.save(person1);
//            session.save(person2);
//            session.save(person3);
//            session.save(person4);
//
//            List<Person> people = session.createQuery("from Person where age > 30 and name like 'Test%' ")
//                    .getResultList();
//
//            people.forEach(p -> System.out.println(p.getName()));
//            Person person = session.get(Person.class , 4L);
//
//            List<Order> orders = person.getOrders();
//
//            orders.forEach(System.out::println);