package org.example.service.impl;

import org.example.dao.BranchDao;
import org.example.entity.Branch;
import org.example.service.BranchService;

import java.util.List;
import java.util.Map;

public class BranchServiceImpl implements BranchService {

    private final BranchDao branchDao;

    public BranchServiceImpl(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public List<Map<Integer, String>> getBranches() {
        List<Map<Integer, String>> branches = branchDao.getBranches();

        if (branches.isEmpty()) {
            throw new IllegalStateException("No branches in the system!");
        }

        return branches;
    }

    @Override
    public Branch getBranchById(int id) {
        return branchDao.getBranchById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect branch id!"));
    }
}
