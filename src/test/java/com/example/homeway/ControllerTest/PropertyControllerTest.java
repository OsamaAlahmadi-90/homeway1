package com.example.homeway.ControllerTest;

import com.example.homeway.Controller.PropertyController;
import com.example.homeway.DTO.In.PropertyDTOIn;
import com.example.homeway.Model.Property;
import com.example.homeway.Model.User;
import com.example.homeway.Service.PropertyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class PropertyControllerTest {

    @Mock
    PropertyService propertyService;

    @InjectMocks
    PropertyController controller;

    MockMvc mockMvc;

    User mockUser;

    private HandlerMethodArgumentResolver authPrincipalResolver(User fixedUser) {
        return new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.hasParameterAnnotation(AuthenticationPrincipal.class)
                        && User.class.isAssignableFrom(parameter.getParameterType());
            }

            @Override
            public Object resolveArgument(MethodParameter parameter,
                                          ModelAndViewContainer mavContainer,
                                          org.springframework.web.context.request.NativeWebRequest webRequest,
                                          org.springframework.web.bind.support.WebDataBinderFactory binderFactory) {
                return fixedUser;
            }
        };
    }

    @BeforeEach
    void setup() {
        mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("customer1");

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setCustomArgumentResolvers(authPrincipalResolver(mockUser))
                .build();
    }

    @Test
    void createProperty_returnsOk_andCallsService() throws Exception {
        PropertyDTOIn dto = new PropertyDTOIn();
        dto.setAddress("Riyadh");
        dto.setNickname("My Home");
        dto.setType("Apartment");

        mockMvc.perform(post("/api/v1/properties/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "address": "Riyadh",
                                  "nickname": "My Home",
                                  "type": "Apartment"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Property created successfully"));

        ArgumentCaptor<PropertyDTOIn> dtoCap = ArgumentCaptor.forClass(PropertyDTOIn.class);
        verify(propertyService).createProperty(eq(mockUser), dtoCap.capture());
        verifyNoMoreInteractions(propertyService);

        PropertyDTOIn captured = dtoCap.getValue();
        org.junit.jupiter.api.Assertions.assertEquals("Riyadh", captured.getAddress());
    }

    @Test
    void getMyProperties_returnsList() throws Exception {
        Property property = new Property();
        property.setId(10);
        property.setAddress("Jeddah");

        when(propertyService.getMyProperties(mockUser))
                .thenReturn(List.of(property));

        mockMvc.perform(get("/api/v1/properties/my"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address").value("Jeddah"));

        verify(propertyService).getMyProperties(mockUser);
        verifyNoMoreInteractions(propertyService);
    }

    @Test
    void updateProperty_returnsOk_andCallsService() throws Exception {

        mockMvc.perform(put("/api/v1/properties/update/{id}", 5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "address": "Dammam",
                                  "nickname": "Updated",
                                  "type": "Villa"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Property updated successfully"));

        verify(propertyService)
                .updateProperty(eq(mockUser), eq(5), any(PropertyDTOIn.class));
        verifyNoMoreInteractions(propertyService);
    }

    @Test
    void deleteProperty_returnsOk_andCallsService() throws Exception {

        mockMvc.perform(delete("/api/v1/properties/delete/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("Property deleted successfully"));

        verify(propertyService).deleteProperty(mockUser, 3);
        verifyNoMoreInteractions(propertyService);
    }


    @Test
    void getMyProperties_emptyList() throws Exception {

        when(propertyService.getMyProperties(mockUser))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/properties/my"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(propertyService).getMyProperties(mockUser);
        verifyNoMoreInteractions(propertyService);
    }
}
