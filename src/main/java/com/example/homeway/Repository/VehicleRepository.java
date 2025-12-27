package com.example.homeway.Repository;

import com.example.homeway.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Vehicle findVehicleById(Integer id);

    @Query("""
            select v from Vehicle v
            where v.company.id = ?1
              and v.available = true
              and v.company.user.role = 'MOVING_COMPANY'
           """)
    Vehicle findAvailableMovingVehicle(Integer companyId);
}