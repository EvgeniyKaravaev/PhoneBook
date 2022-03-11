package ru.ispu.todo.crudtodo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.ispu.todo.crudtodo.controller.ToDoController;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CrudToDoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToDoController toDoController;

    @WithUserDetails
    @Test
    public void testToDoAuth() throws Exception {
        this.mockMvc.perform(get("/views"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    public void testLogin() throws Exception{
        this.mockMvc.perform(get("/views"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testCorrectLogin() throws Exception{
        this.mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("user").password("password"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testNoCorrectLogin() throws Exception{
        this.mockMvc.perform(post("/login").param("user","users"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}