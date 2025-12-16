package com.timeoutairline.epiline.service;

import com.timeoutairline.epiline.model.Employee;
import com.timeoutairline.epiline.model.User;
import com.timeoutairline.epiline.repository.EmployeeRepository;
import com.timeoutairline.epiline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Optional<Employee> getEmployeeByEmpNum(Integer empNum) {
        return employeeRepository.findByEmpNum(empNum);
    }

    public Optional<Employee> getEmployeeByUserId(Long userId) {
        return employeeRepository.findByUser_Id(userId);
    }

    public List<Employee> getEmployeesByProfession(String profession) {
        return employeeRepository.findByProfession(profession);
    }

    public List<Employee> getEmployeesByTitle(String title) {
        return employeeRepository.findByTitle(title);
    }

    public List<Employee> getEmployeesByProfessionAndTitle(String profession, String title) {
        return employeeRepository.findByProfessionAndTitle(profession, title);
    }

    public List<Employee> searchEmployeesByFirstName(String firstname) {
        return employeeRepository.findByUserFirstnameContainingIgnoreCase(firstname);
    }

    public List<Employee> searchEmployeesByLastName(String lastname) {
        return employeeRepository.findByUserLastnameContainingIgnoreCase(lastname);
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByUserEmail(email);
    }

    public Employee saveEmployee(Employee employee) {
        User realUser = userRepository.findById(employee.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + employee.getUserId()));
        employee.setUser(realUser);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
        if (employeeRepository.existsById(employeeId)) {
            updatedEmployee.setEmployeeId(employeeId);
            return employeeRepository.save(updatedEmployee);
        }
        return null;
    }

    public boolean deleteEmployee(Long employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return true;
        }
        return false;
    }

    public boolean empNumExists(Integer empNum) {
        return employeeRepository.existsByEmpNum(empNum);
    }

    public boolean employeeExistsByUserId(Long userId) {
        return employeeRepository.existsByUser_Id(userId);
    }
}