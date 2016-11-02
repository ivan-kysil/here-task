package here.controllers;

import here.dto.Goods;
import here.dto.GoodsTaxResponse;
import here.services.TaxCalculationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TaxCalculatorController {

    @Autowired
    private TaxCalculationService taxCalculationService;

    private static Logger LOG = Logger.getLogger(RestResponseEntityExceptionHandler.class);

    @PostMapping(path = "/tax", produces = "application/json", consumes = "application/json")
    public ResponseEntity<GoodsTaxResponse> calculateTaxes(@RequestBody Goods[] goodsArray) {

        for (Goods goods: goodsArray) {
            LOG.info("goods:" + goods);
        }

        double tax = taxCalculationService.calculateTax(goodsArray);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<GoodsTaxResponse>(new GoodsTaxResponse(String.format("%.2f", tax)), headers, HttpStatus.CREATED);
    }

    public TaxCalculationService getTaxCalculationService() {
        return taxCalculationService;
    }

    public void setTaxCalculationService(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }
}