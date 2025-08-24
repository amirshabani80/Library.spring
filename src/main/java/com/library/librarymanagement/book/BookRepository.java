package com.library.librarymanagement.book;

import org.springframework.data.jpa.repository.*;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {

}
