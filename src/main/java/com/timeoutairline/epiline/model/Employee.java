package com.timeoutairline.epiline.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(unique = true, nullable = false)
    private Integer empNum;

    @Column(nullable = false)
    private String profession;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructors
    public Employee() {
    }

    public Employee(Long employeeId, Integer empNum, String profession, String title, User user) {
        this.employeeId = employeeId;
        this.empNum = empNum;
        this.profession = profession;
        this.title = title;
        this.user = user;
    }

    // Getters
    public Long getEmployeeId() {
        return employeeId;
    }

    public Integer getEmpNum() {
        return empNum;
    }

    public String getProfession() {
        return profession;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    // Setters
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmpNum(Integer empNum) {
        this.empNum = empNum;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("userId")
    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    @JsonProperty("userId")
    public void setUserId(Long userId) {
        if (userId != null) {
            if (this.user == null) {
                this.user = new User();
            }
            this.user.setId(userId);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", empNum=" + empNum +
                ", profession='" + profession + '\'' +
                ", title='" + title + '\'' +
                ", user=" + user +
                '}';
    }
}