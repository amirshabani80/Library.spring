package com.library.librarymanagement.borrow;

import org.springframework.data.jpa.repository.*;

public interface BorrowRepository extends JpaRepository<BorrowEntity,Integer> {
}
