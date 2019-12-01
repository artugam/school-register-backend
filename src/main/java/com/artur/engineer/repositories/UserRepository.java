package com.artur.engineer.repositories;

import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.Role;
import com.artur.engineer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByPasswordResetToken(String token);

    Optional<User> findByEmailAndIdNot(String email, Long id);

    Optional<User> findById(Long id);

    @Query("select u from User u join u.roles r where r.name IN (:role) AND r.name NOT IN :notRoles AND (u.firstName LIKE %:search% OR u.lastName LIKE %:search% OR u.email LIKE %:search%)")
    Page<User> findByFirstNameContainingOrLastNameContainingOrEmailContainingAndRolesIn(
            @Param("search") String search,
//            List<String> role,
            @Param("role") List<String> role,
            @Param("notRoles") List<String> notRoles,
            Pageable pageable
    );

    @Query("select u from User u join u.courses c where c.id = :courseId AND (u.firstName LIKE %:search% OR u.lastName LIKE %:search% OR u.email LIKE %:search%)")
    Page<User> findAllByCourseIdCustomQuery(
            @Param("courseId") Long courseId,
            @Param("search") String search,
            Pageable pageable
    );

    Collection<User> findAllByCoursesIn(
            Collection<Course> courses
    );

    @Query("select u from User u join u.groups c where c.id = :courseGroupId AND (u.firstName LIKE %:search% OR u.lastName LIKE %:search% OR u.email LIKE %:search%)")
    Page<User> findAllByCourseGroupId(
            @Param("courseGroupId") Long courseGroupId,
            @Param("search") String search,
            Pageable pageable
    );

    @Query("select u from User u join u.groups c where c.id = :courseGroupId")
    Collection<User> findAllByCourseGroupId(
            @Param("courseGroupId") Long courseGroupId,
            Sort sort
    );


    void deleteById(Long id);

    List<User> findAllByIdIn(List<Long> userIds);

    List<User> findAll();

    List<User> findAllByOrderByLastNameAsc();

    @Query("select u from User u join u.roles r where r.name = :roleName")
    Collection<User> findAllByRoleName(@Param("roleName") String roleName);

}
