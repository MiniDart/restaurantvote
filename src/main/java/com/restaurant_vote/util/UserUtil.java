package com.restaurant_vote.util;

import com.restaurant_vote.model.Role;
import com.restaurant_vote.model.User;
import com.restaurant_vote.to.UserTo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UserUtil {
    public static User convert(UserTo userTo){
        User user=new User();
        user.setEmail(userTo.getEmail());
        user.setEnabled(true);
        Set<Role> roles=new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        user.setRegistered(LocalDateTime.now());
        user.setName(userTo.getName());
        return user;
    }
}
