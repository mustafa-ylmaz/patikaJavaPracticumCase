package dev.patika.firstcase.controller;


import dev.patika.firstcase.entity.Product;
import dev.patika.firstcase.entity.ProductComment;
import dev.patika.firstcase.entity.User;
import dev.patika.firstcase.repository.ProductCommentRepository;
import dev.patika.firstcase.repository.ProductRepository;
import dev.patika.firstcase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {


    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public ProductCommentRepository productCommentRepository;

    // Product Comment List
    @GetMapping("api/productcomment/{id}")
    public ResponseEntity productComment(@PathVariable Integer id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            List<ProductComment> productCommentList = product.get().getProductComments();
            return ResponseEntity.ok().body(productCommentList);
        }
        return null;
    }

    //Product Comment List with date
    @GetMapping("api/product/CommentDateBetween")
    public ResponseEntity productCommentBetweenDates(@RequestParam(name = "productId") Integer id, @RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) {

        List<ProductComment> productCommentList = productCommentRepository.getAllProductBetweenDates(id, startDate, endDate);
        if (!productCommentList.isEmpty()) {
            return ResponseEntity.ok().body(productCommentList);
        }
        return null;
    }

    //User Comment List
    @GetMapping("/api/user/{id}")
    public ResponseEntity getUserComments(@PathVariable Integer id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            List<ProductComment> userProductComments = user.get().getProductCommentList();
            return ResponseEntity.ok().body(userProductComments);
        }
        return null;
    }

    //User Comment List with date
    @GetMapping("api/user/CommentDateBetween")
    public ResponseEntity userCommentBetweenDates(@RequestParam(name = "userId") Integer id, @RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate) {
        List<ProductComment> productCommentList = productCommentRepository.getAllUserBetweenDates(id, startDate, endDate);

        if(!productCommentList.isEmpty()) {
            return ResponseEntity.ok().body(productCommentList);
        }
        return null;
    }



    //Expired Products
    @GetMapping("/api/product/Expired")
    public ResponseEntity getAllExpiredProducts() {
        List<Product> expiredProducts = productRepository.getAllExpiredProducts();
        if(!expiredProducts.isEmpty())
        {
            return ResponseEntity.ok().body(expiredProducts);
        }
        return null;
    }

    //Not Expired Products
    @GetMapping("/api/product/NotExpired")
    public ResponseEntity getAllNotExpiredProducts() {
        List<Product> notExpiredProducts = productRepository.getAllNotExpiredProducts();
        if (!notExpiredProducts.isEmpty()) {
            return ResponseEntity.ok().body(notExpiredProducts);
        }
        return null;

    }


}
