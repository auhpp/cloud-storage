package com.cloud_storage.repository;

import com.cloud_storage.entity.UserVIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserVIP, Long> {
}
