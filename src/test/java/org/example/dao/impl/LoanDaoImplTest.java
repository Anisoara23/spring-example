package org.example.dao.impl;

import org.example.entity.Loan;
import org.example.pojo.CustomerFinancialProfile;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtils.BRANCH;
import static util.TestUtils.CUSTOMER;
import static util.TestUtils.LOAN;
import static util.TestUtils.SELECT_LOANS;
import static util.TestUtils.SELECT_LOAN_BY_ID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "/configuration/utils-config.xml",
        "/configuration/dao-layer-config.xml",
        "/configuration/datasource-config.xml",
        "/configuration/hibernate-config.xml"
})
public class LoanDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private LoanDaoImpl loanDao;

    @Before
    public void setUp() throws Exception {
        LOAN.setBranch(BRANCH);
        LOAN.setCustomers(Set.of(CUSTOMER));

        verifyInitialDataInLoanTable();
    }

    @Test
    @Transactional
    @Rollback
    public void testAddLoan() {
        String loanId = getAddedLoanId();

        assertEquals(loanId, LOAN.getId());
    }

    @Test
    @Transactional
    public void testGetCustomersWithLoansIds() {
        List<CustomerFinancialProfile> customersWithLoansIds = loanDao.getCustomersWithLoansIds();

        assertFalse(customersWithLoansIds.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveLoanById() {
        String loanId = getAddedLoanId();

        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_LOAN_BY_ID);
        query.setParameter("id", loanId);
        Loan addedLoan = (Loan) query.list().get(0);

        assertEquals(addedLoan, LOAN);

        loanDao.removeLoanById(loanId);
        List list = query.list();

        assertTrue(list.isEmpty());
    }

    private String getAddedLoanId() {
        String loanId = loanDao.addLoan(LOAN);
        sessionFactory.getCurrentSession().flush();
        return loanId;
    }

    private void verifyInitialDataInLoanTable() {
        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_LOANS);
        List list = query.list();
        assertFalse(list.contains(LOAN));
    }
}