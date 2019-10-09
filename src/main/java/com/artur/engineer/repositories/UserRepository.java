package com.artur.engineer.repositories;

import com.artur.engineer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndIdNot(String email, Long id);

    Optional<User> findById(Long id);

//    @Query("select u from User u where u.lastname like ?1%")
//    Collection<User> findByAndSort(String lastname, Sort sort);

//    @Query("SELECT u FROM User u INNER JOIN u.roles r WHERE u.firstName LIKE %:search% OR u.lastName LIKE %:search%")
//    Page<User> findByFirstNameContainingOrLastNameContainingOrEmailContaining(Pageable pageable, @Param("search") String search);
//    User findByLastnameOrFirstname(Pageable pageable, @Param("search") String lastname);
//
    Page<User> findByFirstNameContainingOrLastNameContainingOrEmailContaining(
            Pageable pageable,
            String firstNameSearch,
            String lastNameSearch,
            String emailSearch
    );


    void deleteById(Long id);
}
