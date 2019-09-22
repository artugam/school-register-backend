package com.artur.engineer.repositories;


import com.artur.engineer.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);

    List<Role> findAllByNameIn(Iterable roleNames);



    Optional<Role> findById(Long id);
}
