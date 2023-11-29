package org.example.dao.impl;

import config.TestConfig;
import org.example.config.HibernateConfig;
import org.example.entity.Bank;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static utils.TestUtils.BANK;
import static utils.TestUtils.SELECT_BANKS;
import static utils.TestUtils.SELECT_BANK_BY_NAME;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class BankDaoImplTest {

    @Autowired
    private EmbeddedDatabase embeddedDatabase;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BankDaoImpl bankDao;

    private int numberOfBanks;

    @Test
    @Transactional
    @Rollback
    public void testAddBank() {
        Bank addedBank = getAddedBank();

        assertEquals(BANK, addedBank);
    }

    @Test
    @Transactional
    @Rollback
    public void testGetBankByCode() {
        Bank savedBank = getAddedBank();
        Optional<Bank> bankByCode = bankDao.getBankByCode(savedBank.getCode());

        assertEquals(bankByCode.get(), savedBank);
    }

    @Test
    @Transactional
    public void testGetBankCodes() {
        List<Map<String, String>> bankCodes = bankDao.getBankCodes();

        assertEquals(numberOfBanks, bankCodes.size());
    }

    private Bank getAddedBank() {
        bankDao.addBank(BANK);
        sessionFactory.getCurrentSession().flush();

        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_BANK_BY_NAME);
        query.setParameter("name", BANK.getName());
        List list = query.list();
        return (Bank) list.get(0);
    }

    private void verifyInitialDataFromBankTable() {
        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_BANKS);
        List list = query.list();
        numberOfBanks = list.size();

        assertFalse(list.contains(BANK));
        assertTrue(list.isEmpty());
    }
}