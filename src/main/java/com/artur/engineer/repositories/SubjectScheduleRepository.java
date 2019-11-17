package com.artur.engineer.repositories;


import com.artur.engineer.entities.CourseGroup;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.SubjectSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public interface SubjectScheduleRepository extends CrudRepository<SubjectSchedule, Integer> {

    Optional<SubjectSchedule> findById(Long id);

    void deleteById(Long id);

    @Query("select u from SubjectSchedule u join u.subject c where c.id = :subjectId")
    Page<SubjectSchedule> findAllBySubjectId(
            @Param("subjectId") Long subjectId,
            Pageable pageable
    );

    Collection<SubjectSchedule> findAllBySubject(
            Subject subject,
            Sort sort
    );

    @Query("select u from SubjectSchedule u " +
            "join u.subject s " +
            "WHERE s.group IN (:groups) " +
            "AND u.start > :dateStart " +
            "AND u.end < :dateEnd ")
    Collection<SubjectSchedule> getScheduleForGroups(
            @Param("groups") Collection<CourseGroup> groups,
            @Param("dateStart") Date start,
            @Param("dateEnd") Date end,
            Sort sort
    );
}
