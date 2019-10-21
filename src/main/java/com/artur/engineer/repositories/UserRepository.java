package com.artur.engineer.repositories;

import com.artur.engineer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
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

    @Query("select u from User u join u.courses c where c.id = :courseId AND (u.firstName LIKE %:search% OR u.lastName LIKE %:search% OR u.email LIKE %:search%)")
    Page<User> findAllByCourseId(
            @Param("courseId") Long courseId,
            @Param("search") String search,
            Pageable pageable
    );

    void deleteById(Long id);

    List<User> findAllById(List<Long> userIds);

    List<User> findAll();


//    @Query("select c from Course u LEFT JOIN u.users c WHERE c.id NOT IN :course OR c.courses IS EMPTY ")
//    List<User> findAllByCoursesIsNot(@Param("courses") Long course);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name NOT IN (:roles)")
    List<User> findAllByRolesNameIsNotIn(@Param("roles") List<String> roles);
//    @Query(
//        value = "SELECT * " +
//                "FROM user u " +
//                "INNER JOIN user_role ur ON u",
//        nativeQuery = true)




//    List<User> findAllByCoursesIdIsNot(Long courseId);
}
