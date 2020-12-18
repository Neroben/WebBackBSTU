package back.controllers;


import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest extends ApiBaseTest {

    @SneakyThrows
    @Test
    public void addBook() {
        mockMvc.perform(post("/api/v1/book").content(getBookDto(null)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void addChapterBook() {
        mockMvc.perform(post("/api/v1/book/chapter").contentType(MediaType.APPLICATION_JSON).content(getChapterBookDto())).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void getBookChapter() {
        mockMvc.perform(get("/api/v1/book/chapter/1/0")).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void deleteBook() {
        mockMvc.perform(delete("/api/v1/book/3")).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void deleteChapterBook() {
        mockMvc.perform(delete("/api/v1/book/chapter").param("bookId", "2").param("chapterId", "0")).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void updateBook() {
        mockMvc.perform(put("/api/v1/book").content(getBookDto(2L)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void getAllBook() {
        mockMvc.perform(get("/api/v1/book/all")).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void download() {
        mockMvc.perform(get("/api/v1/book/logo/1")).andExpect(status().isOk());
    }
}