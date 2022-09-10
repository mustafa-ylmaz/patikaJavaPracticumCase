package dev.patika.firstcase.repository;


import dev.patika.firstcase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface UserRepository extends JpaRepository<User,Integer> {


}
