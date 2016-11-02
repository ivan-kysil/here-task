package here.services.impl;


import here.dto.Goods;
import here.services.GoodsCategoryGuessService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;


public class GoodsCategoryGuessServiceImplTest {

    private static final List<String> goodsNamesList = Arrays.asList(
        "Book",
        "Chocolate Bar",
        "Music CD",
        "imported box of chocolates",
        "imported bottle of perfume",
        "bottle of perfume",
        "packet of headache pills",
        "box of imported chocolates");

    @Test
    public void emptyKeyWordsTest() {
        GoodsCategoryGuessService categoryGuessService = new GoodsCategoryGuessServiceImpl("", "");
        assertEquals(0, countTaxFreeGoods(categoryGuessService));
        assertEquals(0, countImportedGoods(categoryGuessService));
    }

    @Test
    public void shouldCountTaxFreeTest() {
        assertEquals(2, countTaxFreeGoods(new GoodsCategoryGuessServiceImpl("book music", "")));
        assertEquals(2, countTaxFreeGoods(new GoodsCategoryGuessServiceImpl("book;music", "")));
        assertEquals(2, countTaxFreeGoods(new GoodsCategoryGuessServiceImpl("book,music", "")));
        assertEquals(1, countTaxFreeGoods(new GoodsCategoryGuessServiceImpl("    book     ", "")));
        assertEquals(5, countTaxFreeGoods(new GoodsCategoryGuessServiceImpl("book,music;bottle pills     ", "")));
    }

    @Test
    public void shouldCountImportedTest() {
        assertEquals(3, countImportedGoods(new GoodsCategoryGuessServiceImpl("", "imported")));
        assertEquals(3, countImportedGoods(new GoodsCategoryGuessServiceImpl("", "import")));
        assertEquals(3, countImportedGoods(new GoodsCategoryGuessServiceImpl("", "   imported   ")));
        assertEquals(3, countImportedGoods(new GoodsCategoryGuessServiceImpl("", "imported import")));
    }

    private int countTaxFreeGoods(GoodsCategoryGuessService categoryGuessService) {
        return countGoodsByFunc(categoryGuessService::guessIfTaxFree);
    }

    private int countImportedGoods(GoodsCategoryGuessService categoryGuessService) {
        return countGoodsByFunc(categoryGuessService::guessIfImported);
    }

    private int countGoodsByFunc (Function<Goods, Boolean> func) {
        int count = 0;
        for (String name: goodsNamesList){
            if (func.apply(new Goods(name, 0, 0, null, null))) {
                count++;
            }
        }
        return count;
    }

}
