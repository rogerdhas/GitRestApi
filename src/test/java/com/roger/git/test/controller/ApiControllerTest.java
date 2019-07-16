package com.roger.git.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roger.git.bean.GitRepoStats;
import com.roger.git.bean.RepoStatistics;
import com.roger.git.controller.GitController;
import com.roger.git.service.IGitApi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GitController.class})
public class ApiControllerTest {
   private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @MockBean
    private IGitApi service;

    @Test
    public void basicAuth() throws Exception {
       this.mockMvc.perform(get("/api/security").header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("gituser:gituser".getBytes())))
                .andExpect(status().isOk());
    }


    @Test
    public void retrieveOpenPrTest() throws Exception {
        GitRepoStats stats=new GitRepoStats();
        stats.setCount(10);
        Mockito.when( service.getRepoStatistics(Mockito.anyString(),Mockito.anyString(),Mockito.anyString())).thenReturn(stats);

        String requestJson="{\n" +
                "  \"gitusername\": \"rogerdhas\",\n" +
                "  \"reponame\": \"GitRestApi\",\n" +
                "  \"type\": \"openpr\"\n" +
                "}";
        RepoStatistics request=new RepoStatistics();
        request.setType("openpr");
        request.setRepoName("roger");
        request.setGitUserName("roger");

        this.mockMvc.perform(post("/api/statistics")
                .header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("gituser:gituser".getBytes()))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
