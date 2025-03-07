package com.example.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Rol ismi: örn. "ROLE_USER", "ROLE_ADMIN"
    @Column(nullable = false, unique = true)
    private String name;

    // Getter ve Setter metodları
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
