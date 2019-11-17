package com.artur.engineer.repositories;


import com.artur.engineer.entities.CourseGroup;
import com.artur.engineer.entities.Subject;
import com.artur.engineer.entities.SubjectPresence;
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

public interface SubjectPresenceRepository extends CrudRepository<SubjectPresence, Integer> {

    Optional<SubjectPresence> findById(Long id);

    void deleteById(Long id);

}
