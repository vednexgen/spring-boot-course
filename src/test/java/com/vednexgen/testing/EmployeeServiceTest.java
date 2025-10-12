package com.vednexgen.testing;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> mockList = Arrays.asList(new Employee(1L, "Atrangi"));
        when(repository.findAll()).thenReturn(mockList);

        List<Employee> result = service.getAllEmployees();

        assertEquals(1, result.size());
        assertEquals("Atrangi", result.get(0).getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testAddEmployee() {
        Employee emp = new Employee(2L, "John");
        when(repository.save(emp)).thenReturn(emp);

        Employee result = service.addEmployee(emp);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(repository).save(emp);
    }
}