package com.klu.jfsd;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        // Insert records
        Customer c1 = new Customer("Alice", "alice@example.com", 25, "New York");
        Customer c2 = new Customer("Bob", "bob@example.com", 30, "Los Angeles");
        Customer c3 = new Customer("Charlie", "charlie@example.com", 35, "Chicago");

        session.save(c1);
        session.save(c2);
        session.save(c3);

        transaction.commit();

        // Query using Criteria
        System.out.println("\nCustomers with age > 25:");
        List<Customer> result1 = session.createCriteria(Customer.class)
                .add(Restrictions.gt("age", 25))
                .list();
        result1.forEach(customer -> System.out.println(customer.getName()));

        System.out.println("\nCustomers in Chicago:");
        List<Customer> result2 = session.createCriteria(Customer.class)
                .add(Restrictions.eq("location", "Chicago"))
                .list();
        result2.forEach(customer -> System.out.println(customer.getName()));

        System.out.println("\nCustomers whose name starts with 'A':");
        List<Customer> result3 = session.createCriteria(Customer.class)
                .add(Restrictions.like("name", "A%"))
                .list();
        result3.forEach(customer -> System.out.println(customer.getName()));

        session.close();
        factory.close();
    }
}

