package org.example.dao.impl;

import org.example.dao.CustomerDao;
import org.example.entity.Customer;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final SessionFactory sessionFactory;

    public CustomerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCustomer(Customer customer) {
        sessionFactory
                .getCurrentSession()
                .save(customer);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT c FROM Customer c WHERE c.email = :email");
        query.setParameter("email", email);
        query.setCacheable(true);
        query.setCacheRegion("customer");

        List list = query.list();

        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of((Customer) list.get(0));
    }

    @Override
    public List<String> getEmails() {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT c.email FROM Customer c");
        query.setCacheable(true);
        query.setCacheRegion("customer");

        return query.list();
    }
}
