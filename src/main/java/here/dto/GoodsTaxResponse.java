package here.dto;

public class GoodsTaxResponse {
    private String salesTax;

    public GoodsTaxResponse(String salesTax) {
        this.salesTax = salesTax;
    }

    public String getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(String salesTax) {
        this.salesTax = salesTax;
    }
}
