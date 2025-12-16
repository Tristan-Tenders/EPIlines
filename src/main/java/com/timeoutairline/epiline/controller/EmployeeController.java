package com.timeoutairline.epiline.controller;

import com.timeoutairline.epiline.model.Employee;
import com.timeoutairline.epiline.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/empNum/{empNum}")
    public ResponseEntity<Employee> getEmployeeByEmpNum(@PathVariable Integer empNum) {
        Optional<Employee> employee = employeeService.getEmployeeByEmpNum(empNum);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Employee> getEmployeeByUserId(@PathVariable Long userId) {
        Optional<Employee> employee = employeeService.getEmployeeByUserId(userId);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/profession/{profession}")
    public ResponseEntity<List<Employee>> getEmployeesByProfession(@PathVariable String profession) {
        List<Employee> employees = employeeService.getEmployeesByProfession(profession);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Employee>> getEmployeesByTitle(@PathVariable String title) {
        List<Employee> employees = employeeService.getEmployeesByTitle(title);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search/firstName")
    public ResponseEntity<List<Employee>> searchEmployeesByFirstName(@RequestParam String name) {
        List<Employee> employees = employeeService.searchEmployeesByFirstName(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search/lastName")
    public ResponseEntity<List<Employee>> searchEmployeesByLastName(@RequestParam String name) {
        List<Employee> employees = employeeService.searchEmployeesByLastName(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/search/email")
    public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam String email) {
        Optional<Employee> employee = employeeService.getEmployeeByEmail(email);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        if (employee.getEmpNum() == null) {
            return new ResponseEntity<>("Employee number is required", HttpStatus.BAD_REQUEST);
        }
        if (employeeService.empNumExists(employee.getEmpNum())) {
            return new ResponseEntity<>("Employee with this number already exists", HttpStatus.CONFLICT);
        }
        if (employee.getProfession() == null || employee.getProfession().isEmpty()) {
            return new ResponseEntity<>("Profession is required", HttpStatus.BAD_REQUEST);
        }
        if (employee.getTitle() == null || employee.getTitle().isEmpty()) {
            return new ResponseEntity<>("Title is required", HttpStatus.BAD_REQUEST);
        }
        if (employee.getUserId() == null) {
            return new ResponseEntity<>("User is required", HttpStatus.BAD_REQUEST);
        }
        if (employeeService.employeeExistsByUserId(employee.getUserId())) {
            return new ResponseEntity<>("This user already has an employee profile", HttpStatus.CONFLICT);
        }
        Employee createdEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee.isEmpty()) {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
        if (updatedEmployee.getEmpNum() == null) {
            return new ResponseEntity<>("Employee number is required", HttpStatus.BAD_REQUEST);
        }
        Optional<Employee> duplicateEmployee = employeeService.getEmployeeByEmpNum(updatedEmployee.getEmpNum());
        if (duplicateEmployee.isPresent() && !duplicateEmployee.get().getEmployeeId().equals(id)) {
            return new ResponseEntity<>("Another employee with this number already exists", HttpStatus.CONFLICT);
        }
        if (updatedEmployee.getProfession() == null || updatedEmployee.getProfession().isEmpty()) {
            return new ResponseEntity<>("Profession is required", HttpStatus.BAD_REQUEST);
        }
        if (updatedEmployee.getTitle() == null || updatedEmployee.getTitle().isEmpty()) {
            return new ResponseEntity<>("Title is required", HttpStatus.BAD_REQUEST);
        }
        if (updatedEmployee.getUserId() == null) {
            return new ResponseEntity<>("User is required", HttpStatus.BAD_REQUEST);
        }
        Employee employee = employeeService.updateEmployee(id, updatedEmployee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id)) {
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }
}