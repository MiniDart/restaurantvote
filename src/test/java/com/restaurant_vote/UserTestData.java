package com.restaurant_vote;

import com.restaurant_vote.model.Role;
import com.restaurant_vote.model.User;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestData {
    public static final User ADMIN=new User(100002,"Admin","admin","admin@mail.com", Role.ROLE_ADMIN,Role.ROLE_USER);

    public static final User USER1=new User(100000,"User1","password1","user1@mail.com", Role.ROLE_USER);

    public static final User USER2=new User(100001,"User2","password2","user2@mail.com", Role.ROLE_USER);

    public static void assertMatchUser(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered","vote");
    }

    public static void assertMatchUser(Iterable<User> actual, User... expected) {
        assertMatchUser(actual, Arrays.asList(expected));
    }

    public static void assertMatchUser(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered","vote").isEqualTo(expected);
    }

    public static ResultMatcher contentUserJson(User user){
        return TestUtil.contentJson(user,"registered");
    }

    public static ResultMatcher contentUserJson(User... users){
        return TestUtil.contentJson(users,"registered");
    }

}
