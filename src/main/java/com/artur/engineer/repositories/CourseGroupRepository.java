package com.artur.engineer.repositories;

import com.artur.engineer.entities.CourseGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseGroupRepository extends CrudRepository<CourseGroup, Integer> {

    Optional<CourseGroup> findById(Long id);


    void deleteById(Long id);
}
