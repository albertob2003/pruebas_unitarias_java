public class PurchaseCalculator {
            public static double CalculateSubtotal(double price, int quantity){
                return price*quantity;
            }
            boolean isValidPurchase(double price, int quantity){
                if(price>0 && quantity >0){
                    return true;
                }
                return false;
            }//method validPurchase
    public double calculateDiscount(double subtotal){
                if(subtotal >=1000){
            return subtotal * 0.1;
        } else{
                    return 0.0;
                }
    } //method discount
    public double calculateFinalPrice(double subtotal){
                if(subtotal >=1000){
                    return subtotal - calculateDiscount(subtotal);
                }
                else{
                    return subtotal;
                }
    }//método finalPrice

}// class
