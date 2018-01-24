package com.restaurant_vote.to;

import com.restaurant_vote.model.User;

import java.time.LocalDateTime;

public class UserTo extends BaseNamedTo{

    private String email;
    private LocalDateTime registred;

    public UserTo(User user) {
        super(user.getId(), user.getName());
        this.email = user.getEmail();
        this.registred = user.getRegistered();
    }

    public UserTo() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistred() {
        return registred;
    }

    public void setRegistred(LocalDateTime registred) {
        this.registred = registred;
    }
}
