package org.example.dao.impl;

import org.example.dao.LoanDao;
import org.example.entity.Loan;
import org.example.pojo.CustomerFinancialProfile;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanDaoImpl implements LoanDao {

    private final SessionFactory sessionFactory;

    public LoanDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addLoan(Loan loan) {
        sessionFactory
                .getCurrentSession()
                .save(loan);
    }

    @Override
    public List<CustomerFinancialProfile> getCustomersWithLoansIds() {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT new org.example.pojo.CustomerFinancialProfile(" +
                        "l.id," +
                        "c.firstName," +
                        "c.lastName," +
                        "c.email," +
                        "c.phoneNumber," +
                        "c.idnp) " +
                        "FROM Customer c " +
                        "INNER JOIN c.loans as l");

        return query.list();
    }

    @Override
    public void removeLoanById(String id) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("DELETE FROM Loan l WHERE id = :id");
        query.setParameter("id", id);

        query.executeUpdate();
    }
}
