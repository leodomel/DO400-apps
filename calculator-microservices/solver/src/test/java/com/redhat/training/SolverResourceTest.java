package com.redhat.training;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

@QuarkusTest
@TestHTTPEndpoint(SolverResource.class)
public class SolverResourceTest {
    
    
    @Test
    public void simplesum(){
        //Mockito.when(solverService.solve("2+3")).thenReturn(Float.valueOf("5.0"));
        given()
        .when().get("2+3")
        .then()
        .statusCode(200)
        .body(is("5.0"));
    }

    @Test
    public void simpleMultiply(){
        given()
        .when().get("4*5")
        .then()
        .statusCode(200)
        .body(is("20.0"));
    }


    @Test
    public void wrongValue(){
        given()
        .when().get("2*a")
        .then()
        .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
        
        
    }

}
