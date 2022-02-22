package com.example.gradinfo.repository;

import com.example.gradinfo.entity.SysEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<SysEventEntity, Long> {
    SysEventEntity getSysEventEntityByEventCodeAndAndEventDescription(String eventCode, String eventDescription);
}
