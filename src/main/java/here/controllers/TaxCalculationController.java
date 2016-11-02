package here.controllers;

import here.dto.Goods;
import here.dto.TaxResponse;
import here.services.TaxCalculationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
public class TaxCalculationController {

    @Autowired
    private TaxCalculationService taxCalculationService;

    private static Logger LOG = Logger.getLogger(TaxCalculationController.class);

    @PostMapping(path = "/tax", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Object> calculateTaxes(@RequestBody(required = false) Goods[] goodsArray) {
        LOG.debug("goods:" + Arrays.toString(goodsArray));

        double tax = 0;
        if (goodsArray != null) {
            tax = taxCalculationService.calculateTax(goodsArray);
        }

        return new ResponseEntity<>(new TaxResponse(String.format("%.2f", tax)), new HttpHeaders(), HttpStatus.OK);
    }
}
