package back.repository;

import back.entity.PageBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PageBookRepository extends JpaRepository<PageBook, Long> {

    Optional<PageBook> findByBook_IdAndNumPage(Long bookId, Long numPage);

}
