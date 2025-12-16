package com.timeoutairline.epiline.repository;

import com.timeoutairline.epiline.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmpNum(Integer empNum);

    Optional<Employee> findByUser_Id(Long userId);

    List<Employee> findByProfession(String profession);

    List<Employee> findByTitle(String title);

    List<Employee> findByProfessionAndTitle(String profession, String title);

    List<Employee> findByUserFirstnameContainingIgnoreCase(String firstname);

    List<Employee> findByUserLastnameContainingIgnoreCase(String lastname);

    Optional<Employee> findByUserEmail(String email);

    boolean existsByEmpNum(Integer empNum);

    boolean existsByUser_Id(Long userId);
}