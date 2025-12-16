package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * EmployeeRepository - Data access layer for Employee entity
 * Spring Data JPA automatically implements these methods
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Find employee by employee number
    Optional<Employee> findByEmpNum(Integer empNum);

    // Find employee by user ID
    Optional<Employee> findByUserId(Long userId);

    // Find employees by profession
    List<Employee> findByProfession(String profession);

    // Find employees by title
    List<Employee> findByTitle(String title);

    // Find employees by profession and title
    List<Employee> findByProfessionAndTitle(String profession, String title);

    // Find employees by user first name (partial match)
    List<Employee> findByUserFirstnameContainingIgnoreCase(String firstname);

    // Find employees by user last name (partial match)
    List<Employee> findByUserLastnameContainingIgnoreCase(String lastname);

    // Find employee by user email
    Optional<Employee> findByUserEmail(String email);

    // Check if employee number exists
    boolean existsByEmpNum(Integer empNum);

    // Check if employee exists by user ID
    boolean existsByUserId(Long userId);
}