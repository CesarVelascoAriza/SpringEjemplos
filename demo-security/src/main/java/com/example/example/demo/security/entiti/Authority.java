package com.example.example.demo.security.entiti;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities",
       uniqueConstraints = @UniqueConstraint(columnNames = {"username", "authority"}))
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Clave primaria técnica

    @Column(length = 50, nullable = false)
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false,
                foreignKey = @ForeignKey(name = "fk_authorities_users"))
    private User user;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAuthority() { return authority; }
    public void setAuthority(String authority) { this.authority = authority; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
