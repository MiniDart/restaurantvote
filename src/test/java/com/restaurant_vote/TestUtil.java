package com.restaurant_vote;

import com.restaurant_vote.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import com.restaurant_vote.web.json.JsonUtil;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static com.restaurant_vote.web.json.JsonUtil.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TestUtil {

    private static final int HIGHEST_ID=100029;

    private static int id=HIGHEST_ID;

    public static int getId(){
        return ++id;
    }
    public static void clearId(){
        id=HIGHEST_ID;
    }

    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static ResultActions print(ResultActions action) throws UnsupportedEncodingException {
        System.out.println(getContent(action));
        return action;
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action), clazz);
    }

    public static <T> ResultMatcher contentJson(T expected, String... ignoreProps) {
        return content().json(writeIgnoreProps(expected,ignoreProps));
    }

    public static <T> ResultMatcher contentJson(T[] expected, String... ignoreProps) {
        return content().json(writeIgnoreProps(Arrays.asList(expected),ignoreProps));
    }

    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(writeValue(expected));
    }

    public static <T> ResultMatcher contentJson(T... expected) {
        return content().json(writeValue(expected));
    }

    public static void mockAuthorize(User user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(new AuthorizedUser(user), null, user.getRoles()));
    }

    public static RequestPostProcessor userHttpBasic(User user) {
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
    }

    public static RequestPostProcessor userAuth(User user) {
        return SecurityMockMvcRequestPostProcessors.authentication(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    }
}
