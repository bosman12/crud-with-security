package com.example.spring_example.model;

import com.example.spring_example.model.enums.Role;
import com.example.spring_example.model.enums.Status;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Status status;

}

