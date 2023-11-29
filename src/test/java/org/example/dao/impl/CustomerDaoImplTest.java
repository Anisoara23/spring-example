package org.example.dao.impl;

import org.example.entity.Customer;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtils.CUSTOMER;
import static util.TestUtils.SELECT_CUSTOMER_BY_EMAIL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "/configuration/utils-config.xml",
        "/configuration/dao-layer-config.xml",
        "/configuration/datasource-config.xml",
        "/configuration/hibernate-config.xml"
})
public class CustomerDaoImplTest {

    public static final String SELECT_CUSTOMERS = "SELECT c FROM Customer c";
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CustomerDaoImpl customerDao;

    @Before
    public void setUp() throws Exception {
        verifyInitialDataInCustomerTable();
    }

    @Test
    @Transactional
    @Rollback
    public void testAddCustomer() {
        addCustomer();
        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_CUSTOMER_BY_EMAIL);
        query.setParameter("email", CUSTOMER.getEmail());

        Customer addedCustomer = (Customer) query.list().get(0);

        assertEquals(CUSTOMER, addedCustomer);
    }

    @Test
    @Transactional
    @Rollback
    public void testGetCustomerByEmail() {
        addCustomer();
        Optional<Customer> customerByEmail = customerDao.getCustomerByEmail(CUSTOMER.getEmail());

        assertEquals(CUSTOMER, customerByEmail.get());
    }

    @Test
    @Transactional
    @Rollback
    public void testGetEmails() {
        addCustomer();
        List<String> emails = customerDao.getEmails();

        assertTrue(emails.contains(CUSTOMER.getEmail()));
    }

    private void addCustomer() {
        customerDao.addCustomer(CUSTOMER);
        sessionFactory.getCurrentSession().flush();
    }

    private void verifyInitialDataInCustomerTable() {
        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_CUSTOMERS);
        List list = query.list();
        assertFalse(list.contains(CUSTOMER));
    }
}