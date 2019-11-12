package com.artur.engineer.repositories;


import com.artur.engineer.entities.SubjectSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubjectScheduleRepository extends CrudRepository<SubjectSchedule, Integer> {

    Optional<SubjectSchedule> findById(Long id);

    void deleteById(Long id);

    @Query("select u from SubjectSchedule u join u.subject c where c.id = :subjectId")
    Page<SubjectSchedule> findAllBySubjectId(
            @Param("subjectId") Long subjectId,
            Pageable pageable
    );
}
