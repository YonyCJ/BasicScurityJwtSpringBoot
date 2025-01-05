package com.prapp.examplesecurityjwtbasic.dao.repository;

import com.prapp.examplesecurityjwtbasic.dao.entity.UserProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, UUID> {

    Page<UserProfileEntity> findAll(Pageable pageable);
    Optional<UserProfileEntity> findById(UUID id);

    List<UserProfileEntity> findByUserUsername(String username);

    List<UserProfileEntity> findByUserId(UUID userId);

}
