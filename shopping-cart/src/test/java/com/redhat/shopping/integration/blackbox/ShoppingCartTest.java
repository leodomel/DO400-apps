package com.redhat.shopping.integration.blackbox;

import java.util.Random;

import javax.inject.Inject;

import com.redhat.shopping.cart.AddToCartCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.delete;



@QuarkusTest
public class ShoppingCartTest {

    private int randomQuantity(){
        return (new Random()).nextInt(10) + 1;
    }

    private void addProductToTheCartWithIdAndRandomQuantity(int productId){
        AddToCartCommand productToAdd = new AddToCartCommand(
            productId, 
            this.randomQuantity()
        );

        given()
        .contentType("application/json")
        .body(productToAdd)
        .put("/cart");

    }


    @BeforeEach
    public void clearChart(){
        delete("/cart");
    }

    //@todo: add integration tests
    @Test
    public void removingNonExistingProductInCatalogReturns400(){
        given()
        .pathParam("id", 99)
        .when()
            .delete("/cart/products/{id}")
        .then()
            .statusCode(400);
    }

    @Test
    public void removingNonExistingProductInCartReturns404(){
        given()
        .pathParam("id", 1)
        .when()
            .delete("/cart/products/{id}")
        .then()
            .statusCode(404);
    }

    @Test
    public void removingTheOnlyProductInCartReturns204(){
        this.addProductToTheCartWithIdAndRandomQuantity(1);

        given()
            .pathParam("id", 1)
        .when()
            .delete("/cart/products/{id}")
        .then()
            .statusCode(204);
    }

    public void removingProductFromCartContainingMultipleAndDifferentProductsReturns200(){
        this.addProductToTheCartWithIdAndRandomQuantity(1);
        this.addProductToTheCartWithIdAndRandomQuantity(2);

        given()
            .pathParam("id", 1)
        .when()
            .delete("/cart/products/{id}")
        .then()
            .statusCode(200);
    }
}
