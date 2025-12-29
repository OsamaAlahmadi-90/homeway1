package com.example.homeway.RepositoryTest;

import com.example.homeway.Model.Company;
import com.example.homeway.Model.User;
import com.example.homeway.Model.Vehicle;
import com.example.homeway.Repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleRepositoryTest {

    @Mock
    VehicleRepository vehicleRepository;

    Vehicle vehicle1, vehicle2, vehicle3;
    Company company;
    User user;

    List<Vehicle> vehicles;

    @BeforeEach
    void setUp() {

        // ---------- User ----------
        user = new User();
        user.setId(1);
        user.setUsername("moving_company");
        user.setPassword("123456");
        user.setName("Moving Company");
        user.setEmail("mc@test.com");
        user.setPhone("0500000000");
        user.setCountry("SA");
        user.setCity("Riyadh");
        user.setRole("MOVING_COMPANY");

        // ---------- Company ----------
        company = new Company();
        company.setId(1);
        company.setStatus("approved");
        company.setUser(user);

        // ---------- Vehicles ----------
        vehicle1 = new Vehicle();
        vehicle1.setId(1);
        vehicle1.setPlateNumber("ABC-111");
        vehicle1.setType("Truck");
        vehicle1.setCapacity(5.0);
        vehicle1.setAvailable(true);
        vehicle1.setCompany(company);

        vehicle2 = new Vehicle();
        vehicle2.setId(2);
        vehicle2.setPlateNumber("DEF-222");
        vehicle2.setType("Van");
        vehicle2.setCapacity(2.0);
        vehicle2.setAvailable(false);
        vehicle2.setCompany(company);

        vehicle3 = new Vehicle();
        vehicle3.setId(3);
        vehicle3.setPlateNumber("GHI-333");
        vehicle3.setType("Truck");
        vehicle3.setCapacity(8.0);
        vehicle3.setAvailable(true);
        vehicle3.setCompany(company);

        vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
    }

    // 1️ findVehicleById
    @Test
    public void testFindVehicleById() {
        when(vehicleRepository.findVehicleById(1)).thenReturn(vehicle1);

        Vehicle result = vehicleRepository.findVehicleById(1);

        assertNotNull(result);
        assertEquals("ABC-111", result.getPlateNumber());
        verify(vehicleRepository, times(1)).findVehicleById(1);
    }

    // 2️ findAvailableMovingVehicle
    @Test
    public void testFindAvailableMovingVehicle() {
        when(vehicleRepository.findAvailableMovingVehicle(1)).thenReturn(vehicle1);

        Vehicle result = vehicleRepository.findAvailableMovingVehicle(1);

        assertNotNull(result);
        assertTrue(result.getAvailable());
        assertEquals("MOVING_COMPANY", result.getCompany().getUser().getRole());
        verify(vehicleRepository, times(1)).findAvailableMovingVehicle(1);
    }

    // 3️ findAllByCompany_Id
    @Test
    public void testFindAllByCompanyId() {
        when(vehicleRepository.findAllByCompany_Id(1)).thenReturn(vehicles);

        List<Vehicle> result = vehicleRepository.findAllByCompany_Id(1);

        assertEquals(3, result.size());
        verify(vehicleRepository, times(1)).findAllByCompany_Id(1);
    }

    // 4️ findAllByCompany_IdAndAvailable
    @Test
    public void testFindAllByCompanyIdAndAvailable() {
        List<Vehicle> availableVehicles = List.of(vehicle1, vehicle3);
        when(vehicleRepository.findAllByCompany_IdAndAvailable(1, true))
                .thenReturn(availableVehicles);

        List<Vehicle> result =
                vehicleRepository.findAllByCompany_IdAndAvailable(1, true);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(Vehicle::getAvailable));
        verify(vehicleRepository, times(1))
                .findAllByCompany_IdAndAvailable(1, true);
    }

    // 5️ findAllByCompany_IdAndCapacityGreaterThanEqual
    @Test
    public void testFindAllByCompanyIdAndCapacityGreaterThanEqual() {
        List<Vehicle> largeVehicles = List.of(vehicle3);
        when(vehicleRepository.findAllByCompany_IdAndCapacityGreaterThanEqual(1, 6.0))
                .thenReturn(largeVehicles);

        List<Vehicle> result =
                vehicleRepository.findAllByCompany_IdAndCapacityGreaterThanEqual(1, 6.0);

        assertEquals(1, result.size());
        assertTrue(result.get(0).getCapacity() >= 6.0);
        verify(vehicleRepository, times(1))
                .findAllByCompany_IdAndCapacityGreaterThanEqual(1, 6.0);
    }
}
