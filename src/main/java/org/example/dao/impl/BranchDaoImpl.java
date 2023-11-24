package org.example.dao.impl;

import org.example.dao.BranchDao;
import org.example.entity.Branch;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BranchDaoImpl implements BranchDao {

    private final SessionFactory sessionFactory;

    public BranchDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBranch(Branch branch) {
        sessionFactory
                .getCurrentSession()
                .save(branch);
    }

    @Override
    public Optional<Branch> getBranchById(Integer id) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT b FROM Branch b WHERE id = :id");
        query.setParameter("id", id);
        query.setCacheable(true);
        query.setCacheRegion("branch");

        List list = query.list();

        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of((Branch) list.get(0));
    }

    @Override
    public List<Map<Integer, String>> getBranches() {
        List<Map<Integer, String>> mapList = new ArrayList<>();
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("SELECT b FROM Branch b");
        query.setCacheable(true);
        query.setCacheRegion("branch");

        List<Branch> branches = query.list();

        branches.forEach(
                branch -> mapList.add(Map.of(branch.getId(), branch.getName()))
        );

        return mapList;
    }
}
