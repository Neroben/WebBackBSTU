package back.service;

import back.dto.book.BookDto;
import back.dto.book.ChapterBookDto;
import back.dto.book.ShortBookDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    Long createBook(BookDto book, MultipartFile logo);

    Long addPageBook(ChapterBookDto chapter);

    List<ShortBookDto> getAllBook();

    ChapterBookDto getBookChapter(Long id, Long chapter);

    void deleteBook(Long id);

    void deleteChapterBook(Long bookId, Long chapterId);

    Long updateBook(BookDto dto);

    Long updatePageBook(ChapterBookDto dto);

}
