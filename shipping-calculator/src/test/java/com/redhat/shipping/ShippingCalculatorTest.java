package com.redhat.shipping;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShippingCalculatorTest {

    // @todo: add tests
    /*
    Test removed after added throw exception and onNonSupportedRegionARegionNotFoundExceptionIsRaised method
    @Test
    public void canCalculateTheCostForARegion() throws RegionNotFoundException{
        //Given
        ShippingCalculator  shippingCalculator = new ShippingCalculator();
        //When

        //Then
        assertEquals(0,shippingCalculator.costForRegion("A Region"));
    }

    */
    @Test
    public void onNARegionTheCostIs100() throws RegionNotFoundException {
        // Given a shipping calculator
        ShippingCalculator calculator = new ShippingCalculator();
    
        // When NA is the country region
        int calculatedCost = calculator.costForRegion("NA");
    
        // Then the shipping cost is 100
        assertEquals(100, calculatedCost);
    }

    @Test
    public void onLATAMRegionTheCostIs200() throws RegionNotFoundException {
        // Given a shipping calculator
        ShippingCalculator calculator = new ShippingCalculator();
    
        // When LATAM is the country region
        int calculatedCost = calculator.costForRegion("LATAM");
    
        // Then the shipping cost is 200
        assertEquals(200, calculatedCost);
    }

    @Test
    public void onEMEARegionTheCostIs300() throws RegionNotFoundException{
        //Given a shipping calculator
        ShippingCalculator shippingCalculator = new ShippingCalculator();
        //when EMEA is the country region
        int calculatedCost = shippingCalculator.costForRegion("EMEA");
        //then the shipping cost is 300
        assertEquals(300, calculatedCost);
    }

    @Test
    public void onAPACRegionTheCostIs400() throws RegionNotFoundException {
        // Given a shipping calculator
        ShippingCalculator calculator = new ShippingCalculator();
    
        // When APAC is the country region
        int calculatedCost = calculator.costForRegion("APAC");
    
        // Then the shipping cost is 400
        assertEquals(400, calculatedCost);
    }

    @Test
    public void onNonSupportedRegionARegionNotFoundExceptionIsRaised(){
        //Given
        ShippingCalculator shippingCalculator = new ShippingCalculator();
        //When
        //Exception exception = shippingCalculator.costForRegion("NON");
        //Then
        assertThrows(
            RegionNotFoundException.class, 
        () -> shippingCalculator.costForRegion("Unknown Region")
        );
        //Then));
        }
}
