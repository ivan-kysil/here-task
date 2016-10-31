package here.controllers;

import here.dto.Goods;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static here.dto.GoodsCategory.*;


@RestController
public class GoodsController {

    @RequestMapping("/goods")
    public Goods greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Goods( "Descr", 1.34f, FOOD, true );
    }

}
