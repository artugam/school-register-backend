package com.artur.engineer.repositories;

import com.artur.engineer.entities.CourseGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseGroupRepository extends CrudRepository<CourseGroup, Integer> {

    Optional<CourseGroup> findById(Long id);

    @Query("select u from CourseGroup u JOIN u.course c WHERE c.id = :courseId AND (u.name LIKE %:search%)")
    Page<CourseGroup> findAllByCourseId(
            @Param("courseId") Long courseId,
            @Param("search") String search,
            Pageable pageable
    );

    void deleteById(Long id);
}
