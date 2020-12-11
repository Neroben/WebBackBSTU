package back.service.impl;

import back.dto.BookDto;
import back.dto.ChapterBookDto;
import back.dto.ShortBookDto;
import back.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public Long createBook(BookDto book, MultipartFile logo) {
        return null;
    }

    @Override
    public Long addPageBook(ChapterBookDto chapter) {
        return null;
    }

    @Override
    public List<ShortBookDto> getAllBook() {
        return null;
    }

    @Override
    public ChapterBookDto getBookChapter(Long id, Integer chapter) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public void deleteChapterBook(Long bookId, Long chapterId) {

    }

    @Override
    public Long updateBook(BookDto dto) {
        return null;
    }

    @Override
    public BookDto getBook(Long id) {
        return null;
    }
}
