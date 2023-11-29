package org.example.dao.impl;

import org.example.entity.Branch;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.junit.After;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtils.BANK;
import static util.TestUtils.BRANCH;
import static util.TestUtils.SELECT_BRANCHES;
import static util.TestUtils.SELECT_BRANCH_BY_NAME;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "/configuration/utils-config.xml",
        "/configuration/dao-layer-config.xml",
        "/configuration/datasource-config.xml",
        "/configuration/hibernate-config.xml"
})
public class BranchDaoImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BranchDaoImpl branchDao;

    private int initialBranchesCount;

    @Before
    public void setUp() throws Exception {
        BRANCH.setBank(BANK);
        verifyInitialDataFromBranchTable();
    }

    @After
    public void tearDown() throws Exception {
        BANK.setCode(null);
    }

    @Test
    @Transactional
    @Rollback
    public void testAddBranch() {
        Branch addedBranch = getAddedBranch();

        assertEquals(BRANCH, addedBranch);
    }


    @Test
    @Transactional
    @Rollback
    public void testGetBranchById() {
        Branch addedBranch = getAddedBranch();

        Optional<Branch> branchById = branchDao.getBranchById(addedBranch.getId());

        assertTrue(branchById.isPresent());
        assertEquals(addedBranch, branchById.get());
    }

    @Test
    @Transactional
    public void testGetBranches() {
        List<Map<Integer, String>> branches = branchDao.getBranches();

        assertEquals(initialBranchesCount, branches.size());
    }

    private Branch getAddedBranch() {
        branchDao.addBranch(BRANCH);
        sessionFactory.getCurrentSession().flush();

        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_BRANCH_BY_NAME);
        query.setParameter("name", BRANCH.getName());
        Branch addedBranch = (Branch) query.list().get(0);
        return addedBranch;
    }

    private void verifyInitialDataFromBranchTable() {
        Query query = sessionFactory.getCurrentSession().createQuery(SELECT_BRANCHES);
        List list = query.list();
        initialBranchesCount = list.size();
        assertFalse(list.contains(BRANCH));
    }
}