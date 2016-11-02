package here.dto;

public class TaxResponse {
    private String salesTax;

    public TaxResponse(String salesTax) {
        this.salesTax = salesTax;
    }

    public String getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(String salesTax) {
        this.salesTax = salesTax;
    }
}
