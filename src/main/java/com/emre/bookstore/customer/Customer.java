package com.emre.bookstore.customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;

import com.emre.bookstore.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table
public class Customer implements UserDetails{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    private LocalDate dob;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean locked = false;
    private Boolean enabled = false;

    public Customer() {

    }

//    public Customer(Long id, String username, String email, LocalDate dob) {
//        this.id = id;
//        this.username = username;
//        this.dob = dob;
//        this.email = email;
//    }

    public Customer(Long id, String username, String email, String dob) {
        this.id = id;
        this.username = username;
        this.dob = LocalDate.parse(dob);
        this.email = email;
    }

    public Customer(String username, String email, LocalDate dob) {
        this.username = username;
        this.email = email;
        this.dob = dob;
    }

    public Customer(Long id, String username, LocalDate dob, String email, String password, Role role, Boolean locked, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.role = role;
        this.locked = locked;
        this.enabled = enabled;
    }

    public Customer(String userName, String email, String password, Role role) {
        this.username = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }
}
