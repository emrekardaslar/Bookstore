package com.emre.bookstore.customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
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
    @Transient
    private Integer age;

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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
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

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

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
