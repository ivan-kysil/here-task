package here.dto;

public enum GoodsCategory {

    FOOD(0), MEDICAL(0), BOOKS(0);

    GoodsCategory(float tax) { this.tax = tax;}

    float tax;

    public float getTax() {
        return tax;
    }
}
