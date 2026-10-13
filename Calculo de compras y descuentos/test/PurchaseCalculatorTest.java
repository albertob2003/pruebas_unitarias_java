import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PurchaseCalculatorTest {
    PurchaseCalculator calc = new PurchaseCalculator();
    @Test

    public void pruebaCalculateSubtotal() {
        assertEquals(300, calc.CalculateSubtotal(100, 3));
        assertEquals(100, calc.CalculateSubtotal(100, 1));
        assertEquals(0, calc.CalculateSubtotal(0, 3));
    }//prueba subtotal
    @Test
    void pruebavalidarCompra(){
        assertTrue(calc.isValidPurchase(12.0,8));
        assertFalse(calc.isValidPurchase(0.0,8));
        assertFalse(calc.isValidPurchase(12.0,0));
        assertFalse(calc.isValidPurchase(-60.0,8));
    }
    @Test
    void pruebacalculateDiscount(){
        assertEquals(0.0, calc.calculateDiscount(38.6));
        assertEquals(100.0, calc.calculateDiscount(1000.0));
        assertEquals(120.0, calc.calculateDiscount(1200.0));
    }//prueba Discount
    @Test
    void pruebacalculateFinalPrice(){
        assertEquals(900.0, calc.calculateFinalPrice(900.0));
        assertEquals(1080.0, calc.calculateFinalPrice(1200.0));
        assertEquals(900.0, calc.calculateFinalPrice(1000.0));

    }
}
