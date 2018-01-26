package com.restaurant_vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant_vote.util.MergeRestriction;
import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {


    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
    @JsonIgnore
    private String password;

    @MergeRestriction(roles = {"ROLE_ADMIN"})
    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @MergeRestriction(roles = {"ROLE_ADMIN"})
    @Column(name = "registered")
    @NotNull
    private LocalDateTime registered;

    @MergeRestriction(roles = {"ROLE_ADMIN"})
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    @MergeRestriction
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Vote vote;


    public User() {
    }

    public User(User u) {
        this(u.getId(), u.getName(), u.getPassword(), u.getEmail(),u.isEnabled(), u.getRegistered(), u.getRoles());
    }

    public User(Integer id, String name, String password, String email, Role role, Role... roles) {
        this(id,name,password,email,true, LocalDateTime.now(),EnumSet.of(role,roles));
    }

    public User(Integer id, String name, String password, String email,boolean enabled, LocalDateTime registered, Collection<Role> roles) {
        super(id, name);
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.email=email;
        setRoles(roles);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}