package com.reke.hobby.common.repository;

import com.reke.hobby.common.model.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonInfoRepository extends JpaRepository <PersonInfo, Long> {
}
