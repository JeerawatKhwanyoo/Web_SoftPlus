package com.softplus.spt.service;

import com.softplus.spt.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findByouCode(String ouCode);
}
