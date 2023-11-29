package org.example.dao.impl;

import org.example.entity.Account;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtils.ACCOUNT;
import static util.TestUtils.BRANCH;
import static util.TestUtils.SELECT_ACCOUNTS;
import static util.TestUtils.SELECT_ACCOUNT_BY_ID;
import static util.TestUtils.setNullIds;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "/configuration/utils-config.xml",
        "/configuration/dao-layer-config.xml",
        "/configuration/datasource-config.xml",
        "/configuration/hibernate-config.xml"
})
public class AccountDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AccountDaoImpl accountDao;

    @Before
    public void setUp() throws Exception {
        setNullIds();
        ACCOUNT.setBranch(BRANCH);

        verifyInitialDataInAccountTable();
    }

    @Test
    @Transactional
    @Rollback
    public void testAddAccount() {
        String accountId = getAddedAccountId();

        assertEquals(accountId, ACCOUNT.getId());
    }

    @Test
    @Transactional
    public void testGetCustomersWithAccountsIds() {
        List<CustomerFinancialProfile> customersWithAccountIds = accountDao.getCustomersWithAccountIds();

        assertFalse(customersWithAccountIds.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    public void testRemoveAccountById() {
        String accountId = getAddedAccountId();

        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_ACCOUNT_BY_ID);
        query.setParameter("id", accountId);
        Account addedAccount = (Account) query.list().get(0);

        assertEquals(addedAccount, ACCOUNT);

        accountDao.removeAccountById(accountId);
        List list = query.list();

        assertTrue(list.isEmpty());
    }

    private String getAddedAccountId() {
        String accountId = accountDao.addAccount(ACCOUNT);
        sessionFactory.getCurrentSession().flush();
        return accountId;
    }

    private void verifyInitialDataInAccountTable() {
        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_ACCOUNTS);
        List list = query.list();
        assertFalse(list.contains(ACCOUNT));
    }
}