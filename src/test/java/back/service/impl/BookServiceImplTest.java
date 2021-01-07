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
        ChapterBookDto dto = bookService.getBookChapter(3L, 0L);
        Assertions.assertNotNull(dto);
    }

    @Test
    void deleteBook() {
        bookService.deleteBook(4L);
    }

    @Test
    void deleteChapterBook() {
        bookService.deleteChapterBook(3L, 0L);
    }

    @Test
    void updateBook() {
        BookDto dto = new BookDto();
        dto.setId(1L);
        dto.setName("test");
        dto.setDescription("test");
        Long id = bookService.updateBook(dto);
        Assertions.assertNotNull(id);
    }

    @Test
    void updatePageBook() {
        ChapterBookDto dto = new ChapterBookDto();
        dto.setBookId(1L);
        dto.setText("test");
        dto.setNumChapter(1L);
        Long id = bookService.updatePageBook(dto);
        Assertions.assertNotNull(id);
    }

    @Test
    void downloadPhoto() {
        Assertions.assertNotNull(bookService.downloadPhoto(2L));
    }
}