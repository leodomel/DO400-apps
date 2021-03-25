package com.redhat.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.redhat.training.service.SolverService;

import org.jboss.resteasy.client.exception.ResteasyBadRequestException;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import io.quarkus.test.Mock;

public class MultiplierResourceTest {
    
    SolverService solverService;
    MultiplierResource multiplierResource;

    @BeforeEach
    public void setup() {
        solverService = mock(SolverService.class);
        multiplierResource = new MultiplierResource(solverService);
    }

    @Test
    public void simpleMultiplication(){

        //Given
        Mockito.when(solverService.solve("2")).thenReturn(Float.valueOf(2));
        Mockito.when(solverService.solve("3")).thenReturn(Float.valueOf(3));
        
        //When
        Float result = multiplierResource.multiply("2", "3");
        //That
        assertEquals(6.0f, result);

       }

    @Test
    public void negativeMultiply(){
        //Given
        String i1 = "-2";
        String i2 = "3";
        Mockito.when(solverService.solve(i1)).thenReturn(Float.valueOf(i1));
        Mockito.when(solverService.solve(i2)).thenReturn(Float.valueOf(i2));
        //When
        Float result = multiplierResource.multiply(i1, i2);
        
        //Then
        assertEquals(-6.0f, result);

    }

    @Test
    public void wrongValue(){
        //Given
        String i1 = "2";
        String i2 = "a";
        WebApplicationException cause = new WebApplicationException("Unknown Error", Response.Status.BAD_REQUEST);
        Mockito.when(solverService.solve(i1)).thenReturn(Float.valueOf(i1));
        Mockito.when(solverService.solve(i2)).thenThrow( new ResteasyWebApplicationException(cause));

        //When
        Executable multiplication = () -> multiplierResource.multiply(i1, i2);
        //Then
        assertThrows(ResteasyWebApplicationException.class, multiplication);
    }
    
}
