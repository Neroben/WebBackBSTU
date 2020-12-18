package back.controllers;

import back.dto.book.BookDto;
import back.dto.book.ChapterBookDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@WithMockUser(authorities = {"ROLE_USER"})
public abstract class ApiBaseTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void createMock() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    protected String getBookDto(Long id) throws JsonProcessingException {
        BookDto dto = new BookDto();
        dto.setId(id);
        dto.setName("test");
        dto.setAuthor("test");
        dto.setDescription("test");
        return objectMapper.writeValueAsString(dto);
    }

    protected String getChapterBookDto() throws JsonProcessingException {
        ChapterBookDto dto = new ChapterBookDto();
        dto.setText("test");
        dto.setBookId(1L);
        dto.setNumChapter(3L);
        return objectMapper.writeValueAsString(dto);
    }

}
