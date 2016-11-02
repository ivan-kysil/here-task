package here.services;

import here.dto.Goods;

public interface TaxCalculationService {

    double calculateTax(Goods[] goodsArr);

}
