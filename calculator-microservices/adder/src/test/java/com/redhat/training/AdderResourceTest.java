package com.redhat.training;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.redhat.training.service.SolverService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(AdderResource.class)
public class AdderResourceTest {

    @InjectMock
    @RestClient 
    SolverService solverService;

    @Test
    public void add(){
        //Given
        Mockito.when(solverService.solve("3")).thenReturn(Float.valueOf("3"));
        Mockito.when(solverService.solve("2")).thenReturn(Float.valueOf("2"));

        
        given()
        //When
        .when().get("/3/2")      //Float result = adderResource.add("1", "2");
        //Then  
        .then()                 //assertEquals(5.0f, result);
        .statusCode(200)        //validate status code 200  
        .body(is("5.0"));       //validate body is "5.0"
            
        
    }

    @Test
    public void negativeSum(){
        //Given
        Mockito.when(solverService.solve("-2")).thenReturn(Float.valueOf("-2"));
        Mockito.when(solverService.solve("3")).thenReturn(Float.valueOf("3"));
        given()
        //When
        .when().get("3/-2")
        //Then
        .then()
        .statusCode(200)
        .body(is("1.0"));
    }

    @Test
    public void wrongValue(){
        //Given
        WebApplicationException cause = new WebApplicationException("Unknown Error", Response.Status.BAD_REQUEST);
        Mockito.when(solverService.solve("a")).thenThrow(new ResteasyWebApplicationException(cause));
        Mockito.when(solverService.solve("2")).thenReturn(Float.valueOf("2.0"));
        given()
        //when
        .when().get("a/2")
        //then
        .then()
        .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }

}
