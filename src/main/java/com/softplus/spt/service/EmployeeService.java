package com.softplus.spt.service;

import com.softplus.spt.domain.Employee;

import java.util.List;

public interface EmployeeService  {
    Employee findId(Long id);
    List<Employee> findAll();
}
