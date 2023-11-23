package org.example.service;

import org.example.entity.Branch;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BranchService {

    List<Map<Integer, String>> getBranches();

    Optional<Branch> getBranchById(int i);
}
