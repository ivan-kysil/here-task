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

import java.util.Arrays;


@RestController
public class TaxCalculatorController {

    @Autowired
    private TaxCalculationService taxCalculationService;

    private static Logger LOG = Logger.getLogger(RestResponseEntityExceptionHandler.class);

    @PostMapping(path = "/tax", produces = "application/json", consumes = "application/json")
    public ResponseEntity<GoodsTaxResponse> calculateTaxes(@RequestBody(required = false) Goods[] goodsArray) {
        LOG.debug("goods:" + Arrays.toString(goodsArray));

        if (goodsArray == null) {
            throw new IllegalArgumentException("goodsArray must not be null!");
        }

        double tax = taxCalculationService.calculateTax(goodsArray);

        return new ResponseEntity<GoodsTaxResponse>(new GoodsTaxResponse(String.format("%.2f", tax)), new HttpHeaders(), HttpStatus.OK);
    }
}
