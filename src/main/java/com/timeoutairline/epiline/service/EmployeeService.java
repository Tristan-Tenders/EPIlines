package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Employee;
import com.timeoutairline.epiline.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * EmployeeService - Business logic layer for Employee operations
 * Matches the style of AirportService in your project
 */
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Get all employees
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Get employee by ID
     */
    public Optional<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    /**
     * Get employee by employee number
     */
    public Optional<Employee> getEmployeeByEmpNum(Integer empNum) {
        return employeeRepository.findByEmpNum(empNum);
    }

    /**
     * Get employee by user ID
     */
    public Optional<Employee> getEmployeeByUserId(Long userId) {
        return employeeRepository.findByUserId(userId);
    }

    /**
     * Get employees by profession
     */
    public List<Employee> getEmployeesByProfession(String profession) {
        return employeeRepository.findByProfession(profession);
    }

    /**
     * Get employees by title
     */
    public List<Employee> getEmployeesByTitle(String title) {
        return employeeRepository.findByTitle(title);
    }

    /**
     * Get employees by profession and title
     */
    public List<Employee> getEmployeesByProfessionAndTitle(String profession, String title) {
        return employeeRepository.findByProfessionAndTitle(profession, title);
    }

    /**
     * Search employees by user first name
     */
    public List<Employee> searchEmployeesByFirstName(String firstname) {
        return employeeRepository.findByUserFirstnameContainingIgnoreCase(firstname);
    }

    /**
     * Search employees by user last name
     */
    public List<Employee> searchEmployeesByLastName(String lastname) {
        return employeeRepository.findByUserLastnameContainingIgnoreCase(lastname);
    }

    /**
     * Get employee by user email
     */
    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByUserEmail(email);
    }

    /**
     * Save a new employee
     */
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Update existing employee
     * Returns null if employee doesn't exist
     */
    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
        if (employeeRepository.existsById(employeeId)) {
            updatedEmployee.setEmployeeId(employeeId);
            return employeeRepository.save(updatedEmployee);
        }
        return null;
    }

    /**
     * Delete employee by ID
     * Returns true if deleted, false if not found
     */
    public boolean deleteEmployee(Long employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }

    /**
     * Check if employee number exists
     */
    public boolean empNumExists(Integer empNum) {
        return employeeRepository.existsByEmpNum(empNum);
    }

    /**
     * Check if employee exists by user ID
     */
    public boolean employeeExistsByUserId(Long userId) {
        return employeeRepository.existsByUserId(userId);
    }
}