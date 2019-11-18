package com.artur.engineer.repositories;

import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.Grade;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GradeRepository extends CrudRepository<Grade, Integer> {

    Optional<Grade> findById(Long id);

    void deleteById(Long id);

    Optional<Grade> findByDescriptionAndSubject(
        String description,
        Subject subject
    );

}
