package com.musala.drone.repository;

import com.musala.drone.models.entity.BatteryAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryAuditRepository extends JpaRepository<BatteryAuditEntity, Long> {
}