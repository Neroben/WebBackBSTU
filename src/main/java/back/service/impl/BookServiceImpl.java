package back.service.impl;

import back.dto.BookDto;
import back.dto.ChapterBookDto;
import back.dto.ShortBookDto;
import back.entity.Book;
import back.repository.BookRepository;
import back.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    public Long createBook(BookDto book) {
        return null;
    }

    public List<ShortBookDto> getAllBook() {
        return null;
    }

    public ChapterBookDto getBookChapter(Long id, Integer chapter) {
        return null;
    }

    public void deleteBook(Long id) {

    }

    public Long updateBook(BookDto dto) {
        return null;
    }
}
