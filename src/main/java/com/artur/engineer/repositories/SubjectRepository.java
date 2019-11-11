package com.artur.engineer.repositories;


import com.artur.engineer.entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    Optional<Subject> findById(Long id);

    void deleteById(Long id);

    @Query("select u from Subject u join u.group c where c.id = :courseGroupId AND (u.type LIKE %:search% OR u.hours LIKE %:search% OR u.name LIKE %:search%)")
    Page<Subject> findAllByCourseGroupId(
            @Param("courseGroupId") Long courseGroupId,
            @Param("search") String search,
            Pageable pageable
    );

}
