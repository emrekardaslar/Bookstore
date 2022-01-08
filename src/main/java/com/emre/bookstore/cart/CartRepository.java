package com.emre.bookstore.cart;

import com.emre.bookstore.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT * FROM CART WHERE customer_id = ?1", nativeQuery = true)
    Cart findCartByCustomerId(Long id);

    @Modifying
    @Transactional
    @Query("update Cart c set c.books = :books, c.total = :total where c.customerId = :customerId")
    int updateCart(@Param("books") Book[] books,
                    @Param("total") double total,
                    @Param("customerId") Long customerId);

    @Modifying
    @Transactional
    @Query("delete from Cart c where c.customerId = :customerId")
    void deleteCartWithCustomerId(@Param("customerId") Long customerId);
}
