package com.restaurant_vote;

import com.restaurant_vote.model.Role;
import com.restaurant_vote.model.User;
import com.restaurant_vote.to.UserTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;
    private static AuthorizedUser authorizedUser;
    static {
        authorizedUser=new AuthorizedUser(new User(100002,"User2","admin","admin@mail.com", Role.ROLE_USER,Role.ROLE_ADMIN));
    }

    private final UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userTo = new UserTo(user);
    }

    public static AuthorizedUser safeGet() {
       return authorizedUser;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return get().userTo.getId();
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }
}