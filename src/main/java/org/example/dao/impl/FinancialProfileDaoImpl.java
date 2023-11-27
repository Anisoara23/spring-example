package org.example.dao.impl;

import org.example.dao.FinancialProfileDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class FinancialProfileDaoImpl implements FinancialProfileDao {

    private final SessionFactory sessionFactory;

    public FinancialProfileDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BigDecimal getLoanAmount(String id) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT fp.amount FROM FinancialProfile fp WHERE id = :id");
        query.setParameter("id", id);

        if (!query.list().isEmpty()) {
            return (BigDecimal) query.list().get(0);
        }

        return null;
    }

    @Override
    public int updateAmount(String id, BigDecimal newAmount) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("UPDATE FinancialProfile SET amount = :amount WHERE id = :id");
        query.setParameter("id", id);
        query.setParameter("amount", newAmount);

        return query.executeUpdate();
    }

    @Override
    public boolean existsFinancialProfileById(String id) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT 1 FROM FinancialProfile WHERE id = :id");
        query.setParameter("id", id);

        return query.uniqueResult() != null;
    }
}
