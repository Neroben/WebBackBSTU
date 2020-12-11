package back.controllers;

import back.dto.BookDto;
import back.dto.ChapterBookDto;
import back.dto.ShortBookDto;
import back.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Long> addBook(@RequestBody BookDto book, @RequestParam(required = false)MultipartFile logo) {
        return ResponseEntity.ok().body(bookService.createBook(book, logo));
    }

    @PostMapping("/chapter")
    public ResponseEntity<Long> addChapterBook(@RequestBody ChapterBookDto chapter) {
        return ResponseEntity.ok().body(bookService.addPageBook(chapter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterBookDto> getBookChapter(@PathVariable Long id, @RequestParam("chapter") Integer chapter) {
        return ResponseEntity.ok().body(bookService.getBookChapter(id, chapter));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return HttpStatus.OK;
    }

    @DeleteMapping("/chapter")
    public HttpStatus deleteChapterBook(@RequestParam Long bookId, @RequestParam Long chapterId) {
        bookService.deleteChapterBook(bookId, chapterId);
        return HttpStatus.OK;
    }

    @PutMapping
    public ResponseEntity<Long> updateBook(@RequestBody BookDto book) {
        return ResponseEntity.ok().body(bookService.updateBook(book));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShortBookDto>> getAllBook() {
        return ResponseEntity.ok().body(bookService.getAllBook());
    }

    @GetMapping
    public ResponseEntity<BookDto> getBook(@RequestParam Long id) {
        return ResponseEntity.ok().body(bookService.getBook(id));
    }

}
