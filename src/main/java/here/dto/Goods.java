package here.dto;

public class Goods {

    private String desc;
    private float unitPrice;
    private GoodsCategory category;
    private boolean imported;

    public Goods() { }

    public Goods(String desc, float unitPrice, GoodsCategory category, boolean imported) {
        this.desc = desc;
        this.unitPrice = unitPrice;
        this.category = category;
        this.imported = imported;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public GoodsCategory getCategory() {
        return category;
    }

    public void setCategory(GoodsCategory category) {
        this.category = category;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }
}
