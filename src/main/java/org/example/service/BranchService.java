package org.example.service;

import org.example.entity.Branch;

import java.util.List;
import java.util.Map;

public interface BranchService {

    List<Map<Integer, String>> getBranches();

    Branch getBranchById(int id);
}
