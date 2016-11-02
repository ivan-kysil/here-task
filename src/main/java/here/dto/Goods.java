package here.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Goods {

    private String description;
    private double unitPrice;
    private int count;
    private Boolean taxFree;
    private Boolean imported;

    public Goods() { }

    @JsonCreator public Goods(
            @JsonProperty(value = "description", required = true) String description,
            @JsonProperty(value = "unitPrice", required = true) double unitPrice,
            @JsonProperty(value = "count", required = true) int count,
            @JsonProperty(value = "taxFree")  Boolean taxFree,
            @JsonProperty(value = "imported") Boolean imported) {
        this.description = description;
        this.unitPrice = unitPrice;
        this.count = count;
        this.taxFree = taxFree;
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

    public Boolean getTaxFree() {
        return taxFree;
    }

    public Boolean isTaxFree() {
        return taxFree;
    }

    public void setTaxFree(Boolean taxFree) {
        this.taxFree = taxFree;
    }

    public Boolean isImported() {
        return imported;
    }

    public Boolean getImported() {
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
                ", taxFree=" + taxFree +
                ", imported=" + imported +
                '}';
    }
}
