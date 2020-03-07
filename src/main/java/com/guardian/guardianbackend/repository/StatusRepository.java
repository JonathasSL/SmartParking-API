package com.guardian.guardianbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guardian.guardianbackend.models.Status;

public interface StatusRepository  extends JpaRepository<Status, Long> {

}
