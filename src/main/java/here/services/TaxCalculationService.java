package here.services;

import here.dto.Goods;

public interface TaxCalculationService {

    /**
    * calculates amount of tax that needs to be paid for goods in goodsArr
    */
    double calculateTax(Goods[] goodsArr);

}
