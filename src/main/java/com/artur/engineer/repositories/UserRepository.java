package com.artur.engineer.repositories;

import com.artur.engineer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndIdNot(String email, Long id);

    Optional<User> findById(Long id);

//    @Query("select u from User u where u.lastname like ?1%")
//    Collection<User> findByAndSort(String lastname, Sort sort);

    Page<User> findAll(Pageable pageable);


    void deleteById(Long id);
}
