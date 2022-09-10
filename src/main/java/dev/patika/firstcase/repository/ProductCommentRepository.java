package dev.patika.firstcase.repository;

import dev.patika.firstcase.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {

    @Query(value = "SELECT * FROM product_comment pc WHERE pc.product_id = (?1) AND pc.comment_date BETWEEN (?2) AND (?3);"
            , nativeQuery = true)
    public List<ProductComment> getAllProductBetweenDates(Integer productId,String startDate,String endDate);


    @Query(value = "SELECT * FROM product_comment pc WHERE pc.user_id = (?1) AND pc.comment_date BETWEEN (?2) AND (?3);"
            , nativeQuery = true)
    List<ProductComment> getAllUserBetweenDates(Integer id, String startDate, String endDate);
}
