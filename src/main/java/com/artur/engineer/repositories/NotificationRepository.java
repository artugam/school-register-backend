package com.artur.engineer.repositories;


import com.artur.engineer.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {

    Optional<Notification> findById(Long id);


    void deleteById(Long id);


    @Query("select u from Notification u " +
            "join u.course c " +
            "join u.creator user " +
            "left join u.group g " +
            "left join u.subject s " +
            "where user.id = :userId " +
            "AND (c.name LIKE %:search% OR c.startDate LIKE %:search% OR g.name LIKE %:search% OR s.name LIKE %:search%)")
    Page<Notification> findAllNotifications(
            @Param("userId") Long userId,
            @Param("search") String search,
            Pageable pageable
    );

    @Query("select u from Notification u " +
            "join u.course c " +
            "join u.creator user " +
            "left join u.group g " +
            "left join u.subject s " +
            "WHERE (c.name LIKE %:search% OR c.startDate LIKE %:search% OR g.name LIKE %:search% OR s.name LIKE %:search%)")
    Page<Notification> findAllNotifications(
            @Param("search") String search,
            Pageable pageable
    );


    Collection<Notification> findAllByIdIn(Collection<Long> ids);
}
