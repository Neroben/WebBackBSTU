package back.service.impl;

import back.dto.book.BookDto;
import back.dto.book.ChapterBookDto;
import back.dto.book.ShortBookDto;
import back.service.BookService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

class BookServiceImplTest extends BaseTest {

    @Autowired
    private BookService bookService;

    @Test
    @SneakyThrows
    void createBook() {
        BookDto book = new BookDto();
        book.setDescription("test");
        book.setAuthor("test");
        book.setName("test");

        MultipartFile logo = new MockMultipartFile(
                "test", "test.jpg",
                MediaType.IMAGE_PNG_VALUE,
                new ClassPathResource("unknown-book.jpg", this.getClass().getClassLoader()).getInputStream());

        Long id = bookService.createBook(book, logo);
        Assertions.assertNotNull(id);
    }

    @Test
    void addPageBook() {
        ChapterBookDto book = new ChapterBookDto();
        book.setBookId(3L);
        book.setNumChapter(1L);
        book.setText("test");

        Long id = bookService.addPageBook(book);
        Assertions.assertNotNull(id);
    }

    @Test
    void getAllBook() {
        List<ShortBookDto> list = bookService.getAllBook();
        Assertions.assertNotNull(list);
    }

    @Test
    void getBookChapter() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void deleteChapterBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void updatePageBook() {
    }

    @Test
    void downloadPhoto() {
    }
}