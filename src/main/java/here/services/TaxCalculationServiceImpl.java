package here.services;

import here.dto.Goods;
import here.dto.GoodsCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TaxCalculationServiceImpl implements TaxCalculationService {

    private static final float BASE_TAX = 0.1f;
    private static final float IMPORT_TAX = 0.05f;

    @Autowired
    private RoundingService roundingService;

    @Autowired
    private GoodsCategoryInferService inferService;

    @Override
    public double calculateTax(Goods[] goodsArr) {
        double totalTax = 0;

        for (Goods goods: goodsArr) {
            totalTax += getTotalTax(goods) * goods.getTotalPrice();
        }

        return roundingService.round(totalTax);
    }

    private double getTotalTax(final Goods goods) {
        return getBaseTax(goods) + getImportTax(goods);
    }

    private double getBaseTax(final Goods goods) {
        if (goods.getCategory() != null) {
            return goods.getCategory().getTax();
        }

        final Optional<GoodsCategory> inferred = inferService.inferCategory(goods);
        if (inferred.isPresent()) {
            return inferred.get().getTax();
        }

        return BASE_TAX;
    }

    private double getImportTax(final Goods goods) {
        if (goods.isImported() != null && goods.isImported() || inferService.inferIfImported(goods)) {
            return IMPORT_TAX;
        }
        return 0;
    }

    public RoundingService getRoundingService() {
        return roundingService;
    }

    public void setRoundingService(RoundingService roundingService) {
        this.roundingService = roundingService;
    }

    public GoodsCategoryInferService getInferService() {
        return inferService;
    }

    public void setInferService(GoodsCategoryInferService inferService) {
        this.inferService = inferService;
    }
}
