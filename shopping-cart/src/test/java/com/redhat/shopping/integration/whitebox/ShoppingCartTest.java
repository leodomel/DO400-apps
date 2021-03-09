package com.redhat.shopping.integration.whitebox;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.inject.Inject;

import com.redhat.shopping.cart.CartService;
import com.redhat.shopping.cart.ProductNotInCartException;
import com.redhat.shopping.catalog.ProductNotFoundInCatalogException;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

@QuarkusTest
public class ShoppingCartTest{

    @Inject
    CartService cartService;

    @BeforeEach
    void clearChart(){
        this.cartService.clear();
    }

    //@todo: add integration tests
    @Test
    void addingNonExistingProductInCatalogRaisesAnException() {
        assertThrows(ProductNotFoundInCatalogException.class,
         () -> this.cartService.addProduct(99, 1)  ); 
    }

    @Test
    void addingNonExistingProductInCartTheTotalItemsMatchTheInitialQuantity() throws ProductNotFoundInCatalogException{
        this.cartService.addProduct(1, 10);
        assertEquals(10, this.cartService.totalItems());
    }

    @Test
    void addingProductThatIsInTheCartTheTotalItemsMatchTheSumOfQuantities() throws ProductNotFoundInCatalogException{
        this.cartService.addProduct(1, 10);
        this.cartService.addProduct(1, 100);

        assertEquals(110, this.cartService.totalItems());
    }
}