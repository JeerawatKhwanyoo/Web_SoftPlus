package com.softplus.spt.service;

import com.softplus.spt.domain.Employee;
import com.softplus.spt.repostirory.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServicelmp implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findByouCode(String ouCode) {
        return EmployeeRepository.;
    }
}
