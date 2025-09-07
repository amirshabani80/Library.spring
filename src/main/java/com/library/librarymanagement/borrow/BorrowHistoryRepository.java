package com.library.librarymanagement.borrow;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistoryEntity,Integer> {
    List<BorrowHistoryEntity> findByMemberId(Integer memberId);
    List<BorrowHistoryEntity> findByBookId(Integer bookId);
   BorrowHistoryEntity findByBorrowId(Integer borrowId);
}
