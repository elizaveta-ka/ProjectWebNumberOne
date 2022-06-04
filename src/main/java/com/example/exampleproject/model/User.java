package com.example.exampleproject.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import com.example.exampleproject.model.Role;
import com.example.exampleproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Transient
    transient private String confirmPassword;
        @ManyToOne (optional=false, fetch = FetchType.LAZY)
        @JoinColumn (name="user_role")
        private Role role;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
//        @Autowired
//        RoleRepository rolrep;
//        List rolreps = rolrep.findAll();
//        this.role = (Role) rolreps.get(2);

    }

    public User() {

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
//    @ManyToMany
//    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password,
                role);
    }
}
