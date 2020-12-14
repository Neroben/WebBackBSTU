package back.controllers;

import back.dto.book.BookDto;
import back.dto.book.ChapterBookDto;
import back.dto.book.ShortBookDto;
import back.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @GetMapping("/chapter/{id}/{chapter}")
    public ResponseEntity<ChapterBookDto> getBookChapter(@PathVariable Long id, @PathVariable("chapter") Long chapter) {
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

    @GetMapping(value = "/logo/{bookId}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Resource> download(@PathVariable(value = "bookId") Long id) {
        Resource resource = bookService.downloadPhoto(id);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS).cachePublic())
                .body(resource);
    }

}
