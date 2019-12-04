package com.artur.engineer.repositories;

import com.artur.engineer.entities.Course;
import com.artur.engineer.entities.Grade;
import com.artur.engineer.entities.Subject;
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

public interface GradeRepository extends CrudRepository<Grade, Integer> {

    Optional<Grade> findById(Long id);

    void deleteById(Long id);

    Optional<Grade> findByDescriptionAndSubject(
        String description,
        Subject subject
    );

    @Query("SELECT DISTINCT g.description FROM Grade g WHERE g.subject = :subject")
    List<String> findDistinctDescriptionBySubject(
            Subject subject,
            Sort sort
    );

    @Query("SELECT g FROM Grade g JOIN g.subject s WHERE s.id = :subjectId AND g.description = :descrption")
    Collection<Grade> findByDescriptionBySubject(
            @Param("subjectId") Long subjectId,
            @Param("descrption") String description
    );
}
