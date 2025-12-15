package com.timeoutairline.epiline.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

/**
 * User Entity - JPA entity representing users table in database
 * Uses Lombok @Data to auto-generate getters, setters, toString, equals, hashCode
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private String address;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private LocalDate birthdate;
}
