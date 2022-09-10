package dev.patika.firstcase.repository;

import dev.patika.firstcase.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductRepository extends JpaRepository<Product,Integer> {

        public List<Product> findById(int id);

        @Query(value="SELECT * FROM product p WHERE DATEDIFF(CURDATE(),p.expiration_date)>0",
                nativeQuery = true)
        public List<Product> getAllExpiredProducts();

        @Query(value="SELECT * FROM product p WHERE p.expiration_date is NULL or DATEDIFF(CURDATE(),p.expiration_date)<0",
                nativeQuery = true)
        public List<Product> getAllNotExpiredProducts();



}
