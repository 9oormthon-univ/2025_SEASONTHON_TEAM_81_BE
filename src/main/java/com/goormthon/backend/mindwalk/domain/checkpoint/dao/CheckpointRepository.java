package com.goormthon.backend.mindwalk.domain.checkpoint.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goormthon.backend.mindwalk.domain.checkpoint.domain.Checkpoint;

@Repository
public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {
}
