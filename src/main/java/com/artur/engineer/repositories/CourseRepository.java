package com.artur.engineer.repositories;

import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    Optional<Course> findById(Long id);

    Page<Course> findByNameContainingOrFormContainingOrDegreeContaining(
            Pageable pageable,
            String nameSearch,
            String form,
            String degree
    );

    void deleteById(Long id);
}
