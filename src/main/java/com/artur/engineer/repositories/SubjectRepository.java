package com.artur.engineer.repositories;


import com.artur.engineer.entities.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}
