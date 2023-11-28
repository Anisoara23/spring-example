package org.example.dao.impl;

import org.example.entity.Bank;
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
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static util.TestUtils.BANK;
import static util.TestUtils.SELECT_BANKS;
import static util.TestUtils.SELECT_BANK_BY_NAME;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/configuration/utils-config.xml",
        "/configuration/dao-layer-config.xml",
        "/configuration/datasource-config.xml",
        "/configuration/hibernate-config.xml"})
public class BankDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BankDaoImpl bankDao;

    private int numberOfBanks;

    @Before
    public void setUp() throws Exception {
        verifyInitialDataFromBankTable();
    }

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
    }
}
