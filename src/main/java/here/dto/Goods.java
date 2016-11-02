package here.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Goods {

    private String description;
    private double unitPrice;
    private int count;
    private GoodsCategory category;
    private Boolean imported;

    public Goods() { }

    @JsonCreator public Goods(
            @JsonProperty(value = "description", required = true) String description,
            @JsonProperty(value = "unitPrice", required = true) double unitPrice,
            @JsonProperty(value = "count", required = true) int count,
            @JsonProperty(value = "category") GoodsCategory category,
            @JsonProperty(value = "imported") Boolean imported) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.count = count;
        this.category = category;
        this.imported = imported;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public GoodsCategory getCategory() {
        return category;
    }

    public void setCategory(GoodsCategory category) {
        this.category = category;
    }

    public Boolean isImported() {
        return imported;
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    public double getTotalPrice() {
        return unitPrice * count;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", count=" + count +
                ", category=" + category +
                ", imported=" + imported +
                '}';
    }
}
