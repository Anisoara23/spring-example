package org.example.dao.impl;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtils.BRANCH;
import static util.TestUtils.INITIAL_AMOUNT;
import static util.TestUtils.LOAN;
import static util.TestUtils.NEW_AMOUNT;
import static util.TestUtils.setNullIds;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "/configuration/utils-config.xml",
        "/configuration/dao-layer-config.xml",
        "/configuration/datasource-config.xml",
        "/configuration/hibernate-config.xml"
})
public class FinancialProfileDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private FinancialProfileDaoImpl financialProfileDao;

    @Autowired
    private LoanDaoImpl loanDao;

    @Before
    @Transactional
    @Rollback
    public void setUp() throws Exception {
        setNullIds();
        LOAN.setAmount(INITIAL_AMOUNT);
        LOAN.setBranch(BRANCH);
        loanDao.addLoan(LOAN);
    }

    @Test
    @Transactional
    public void testGetAmount() {
        BigDecimal amount = financialProfileDao.getAmount(LOAN.getId());

        assertEquals(INITIAL_AMOUNT, amount);
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateAmount() {
        financialProfileDao.updateAmount(LOAN.getId(), NEW_AMOUNT);
        BigDecimal updatedAmount = financialProfileDao.getAmount(LOAN.getId());

        assertEquals(NEW_AMOUNT, updatedAmount);
    }

    @Test
    @Transactional
    public void testExistsFinancialProfileById() {
        boolean exist = financialProfileDao.existsFinancialProfileById(LOAN.getId());

        assertTrue(exist);
    }
}