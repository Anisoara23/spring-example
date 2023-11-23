package org.example.service.impl;

import org.example.dao.BranchDao;
import org.example.entity.Branch;
import org.example.service.BranchService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BranchServiceImpl implements BranchService {

    private final BranchDao branchDao;

    public BranchServiceImpl(BranchDao branchDao) {
        this.branchDao = branchDao;
    }

    @Override
    public List<Map<Integer, String>> getBranches() {
        return branchDao.getBranches();
    }

    @Override
    public Optional<Branch> getBranchById(int id) {
        return branchDao.getBranchById(id);
    }
}
