package com.restaurant_vote.web.controllers;

import com.restaurant_vote.TestUtil;
import com.restaurant_vote.model.HistoryMenuItem;
import com.restaurant_vote.model.HistoryVote;
import com.restaurant_vote.model.Role;
import com.restaurant_vote.model.User;
import com.restaurant_vote.to.HistoryMenuItemTo;
import com.restaurant_vote.to.HistoryVoteTo;
import com.restaurant_vote.to.VoteTo;
import com.restaurant_vote.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;


import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.assertj.core.api.Assertions.assertThat;
import static com.restaurant_vote.TestUtil.*;
import static com.restaurant_vote.UserTestData.*;
import static com.restaurant_vote.VoteTestData.*;
import static com.restaurant_vote.HistoryVoteTestData.*;
import static com.restaurant_vote.HistoryMenuTestData.*;
import static com.restaurant_vote.MenuTestData.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminControllerTest extends AbstractControllerTest {

    private static final String URL="/admin";

    @Test
    public void testGetAllUsers() throws Exception{
        mockMvc.perform(get(URL+"/users")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentUserJson(ADMIN,USER1,USER2));
    }

    @Test
    public void testGetUser() throws Exception{
        mockMvc.perform(get(URL+"/users/"+USER1.getId())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentUserJson(USER1));
    }

    @Test
    public void testCreateUser() throws Exception{
        User expected = new User(null, "New", "newPass", "new@gmail.com", Role.ROLE_USER);
        ResultActions action = mockMvc.perform(post(URL+"/users")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        User returned = TestUtil.readFromJson(action, User.class);
        expected.setId(returned.getId());

        assertMatchUser(returned, expected);
        assertMatchUser(userService.getAll(), ADMIN, expected, USER1,USER2);
    }

    @Test
    public void testDeleteUser() throws Exception{
        mockMvc.perform(delete(URL+"/users/"+USER1.getId())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        assertMatchUser(userService.getAll(),ADMIN,USER2);
    }


    @Test
    public void testUpdateUser() throws Exception{
        String oldName=USER1.getName();
        USER1.setName("newName");
        mockMvc.perform(put(URL+"/users/"+USER1.getId())
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(USER1)))
                .andExpect(status().isOk());

        assertMatchUser(USER1,userService.get(USER1.getId()));
        USER1.setName(oldName);
    }


    @Test
    public void testGetUserByEmail() throws Exception{

        mockMvc.perform(get(URL+"/users/by?email="+USER1.getEmail())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentUserJson(USER1));
    }


    @Test
    public void testClear() throws Exception{
        mockMvc.perform(post(URL+"/clear")
            .with(userHttpBasic(ADMIN)))
            .andExpect(status().isNoContent());
        assertThat(menuService.getAll()).hasSize(9);

        assertThat(historyMenuService.getAll())
                .usingFieldByFieldElementComparator()
                .containsExactlyElementsOf(Stream.concat(
                        getMenuAsList().stream()
                                .map(m->{
                                    HistoryMenuItem historyMenuItem=new HistoryMenuItem(m);
                                    historyMenuItem.setId(getId());
                                    return historyMenuItem;
                                }), getHistoryMenuAsList().stream())
                        .sorted(Comparator.comparing(HistoryMenuItem::getId))
                        .map(HistoryMenuItemTo::new)
                        .collect(Collectors.toList()));

        assertThat(voteService.getAll()).hasSize(0);

        assertThat(historyVoteService.getAll())
                .usingFieldByFieldElementComparator()
                .containsExactlyElementsOf(Stream.concat(getVotesAsList().stream()
                                .map(vote -> {
                                    HistoryVote hv = new HistoryVote(vote);
                                    hv.setId(getId());
                                    return hv;
                                }),
                        getHistoryVotesAsList().stream())
                        .sorted(Comparator.comparing(HistoryVote::getId))
                        .map(HistoryVoteTo::new)
                        .collect(Collectors.toList()));
    }

    @Test
    public void testGetAllVotes() throws Exception{
        mockMvc.perform(get(URL+"/votes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(getVotesAsList().stream()
                        .map(VoteTo::new)
                        .collect(Collectors.toList())));
    }


    @Test
    public void testGetUsersHistoryWithoutDate() throws Exception{
        mockMvc.perform(get(URL+"/users/history")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(getHistoryVotesAsList().stream()
                        .map(HistoryVoteTo::new)
                        .collect(Collectors.toList())));
    }

    @Test
    public void testGetUsersHistoryWithDate() throws Exception{
        mockMvc.perform(get(URL+"/users/history?start=2016-03-06&end=2016-03-07")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(Stream.of(HISTORY_VOTE1,HISTORY_VOTE2)
                .map(HistoryVoteTo::new)
                .collect(Collectors.toList())));
    }

    @Test
    public void testGetVotesHistoryByUserWithoutDate() throws Exception{
        mockMvc.perform(get(URL+"/users/"+USER1.getId()+"/history")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(Stream.of(HISTORY_VOTE1,HISTORY_VOTE3)
                        .map(HistoryVoteTo::new)
                        .collect(Collectors.toList())));
    }

    @Test
    public void testGetVotesHistoryByUserWithDate() throws Exception{
        mockMvc.perform(get(URL+"/users/"+USER1.getId()+"/history?start=2016-03-06&end=2016-03-07")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TestUtil.contentJson(new HistoryVoteTo[] {new HistoryVoteTo(HISTORY_VOTE1)}));
    }


    @Test
    public void testGetUnAuth() throws Exception {
        mockMvc.perform(get(URL))
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(URL)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isForbidden());
    }
}
