package com.example.homeway.ServiceTest;

import com.example.homeway.API.ApiException;
import com.example.homeway.DTO.In.PropertyDTOIn;
import com.example.homeway.Model.Customer;
import com.example.homeway.Model.Property;
import com.example.homeway.Model.User;
import com.example.homeway.Repository.CustomerRepository;
import com.example.homeway.Repository.PropertyRepository;
import com.example.homeway.Service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceTest {

    @InjectMocks
    PropertyService propertyService;

    @Mock
    PropertyRepository propertyRepository;

    @Mock
    CustomerRepository customerRepository;

    User user;
    Customer customer;
    Property property;
    PropertyDTOIn dto;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setId(1);

        customer = new Customer();
        customer.setId(1);
        customer.setUser(user);

        property = new Property();
        property.setId(1);
        property.setAddress("Riyadh");
        property.setNickname("My Home");
        property.setType("Apartment");
        property.setCustomer(customer);

        dto = new PropertyDTOIn();
        dto.setAddress("Jeddah");
        dto.setNickname("New Home");
        dto.setType("Villa");
    }

    // 1️ createProperty (SUCCESS)
    @Test
    public void testCreateProperty() {
        when(customerRepository.findCustomerById(1)).thenReturn(customer);

        propertyService.createProperty(user, dto);

        verify(propertyRepository, times(1)).save(any(Property.class));
    }

    // 2️ getMyProperties (SUCCESS)
    @Test
    public void testGetMyProperties() {
        when(customerRepository.findCustomerById(1)).thenReturn(customer);
        when(propertyRepository.findAllByCustomer_Id(1))
                .thenReturn(List.of(property));

        List<Property> result = propertyService.getMyProperties(user);

        assertEquals(1, result.size());
        verify(propertyRepository, times(1))
                .findAllByCustomer_Id(1);
    }

    // 3️ updateProperty (SUCCESS)
    @Test
    public void testUpdateProperty() {
        when(customerRepository.findCustomerById(1)).thenReturn(customer);
        when(propertyRepository.findById(1))
                .thenReturn(Optional.of(property));

        propertyService.updateProperty(user, 1, dto);

        verify(propertyRepository, times(1)).save(property);
    }

    // 4️ updateProperty (NOT OWNER → Exception)
    @Test
    public void testUpdatePropertyNotOwner() {
        Customer otherCustomer = new Customer();
        otherCustomer.setId(2);

        property.setCustomer(otherCustomer);

        when(customerRepository.findCustomerById(1)).thenReturn(customer);
        when(propertyRepository.findById(1))
                .thenReturn(Optional.of(property));

        ApiException exception = assertThrows(
                ApiException.class,
                () -> propertyService.updateProperty(user, 1, dto)
        );

        assertEquals("You are not allowed to update this property", exception.getMessage());
    }

    // 5️ deleteProperty (SUCCESS)
    @Test
    public void testDeleteProperty() {
        when(customerRepository.findCustomerById(1)).thenReturn(customer);
        when(propertyRepository.findById(1))
                .thenReturn(Optional.of(property));

        propertyService.deleteProperty(user, 1);

        verify(propertyRepository, times(1)).delete(property);
    }
}
