package here.services;

import here.dto.Goods;
import here.dto.GoodsCategory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TaxCalculationServiceImplTest {

    private RoundingService roundingServiceMock = mock(RoundingService.class);
    private TaxCalculationService taxCalculationService;
    {
        TaxCalculationServiceImpl taxCalculationServiceImpl = new TaxCalculationServiceImpl();
        taxCalculationServiceImpl.setRoundingService(roundingServiceMock);
        taxCalculationService = taxCalculationServiceImpl;
    }

    @Before
    public void before() {
        reset(roundingServiceMock);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(roundingServiceMock);
    }

    @Test
    public void nullArgTest() {
        assertEquals(0., taxCalculationService.calculateTax(null), 0.);
    }

    @Test
    public void emptyArrayArgTest() {
        assertEquals(0., taxCalculationService.calculateTax(new Goods[0]), 0.);
    }

    @Test
    public void shouldCountTaxTest() {
        Goods[] goodsArr = new Goods[] {
                new Goods("descr", 1.0, 2, null, null),                 // base tax = 0.1 * 2 = 0.2
                new Goods("descr", 10.0, 1, GoodsCategory.BOOKS, null), // tax = 0
                new Goods("descr", 20.0, 1, GoodsCategory.FOOD, true),  // import tax = 20.0 * 5% = 1
                new Goods("descr", 100.0, 3, null, true),               // import tax = 5%; base tax = 10%; tax = 100 *3 * 15% = 45
        };                                                              // total tax = 45 + 1 + 0.2 = 46.2
        taxCalculationService.calculateTax(goodsArr);
        ArgumentCaptor<Double> taxCaptor = ArgumentCaptor.forClass(Double.class);
        verify(roundingServiceMock).round(taxCaptor.capture());
        assertEquals(46.2, taxCaptor.getValue(), 0.0001);
    }
}
