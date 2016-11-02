package here.services.impl;

import here.dto.Goods;
import here.services.GoodsCategoryGuessService;
import here.services.RoundingService;
import here.services.TaxCalculationService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TaxCalculationServiceImplTest {
    @Mock
    private RoundingService roundingServiceMock;
    @Mock
    private GoodsCategoryGuessService guessServiceMock;
    @InjectMocks
    private TaxCalculationService taxCalculationService = new TaxCalculationServiceImpl();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        reset(roundingServiceMock, guessServiceMock);
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
        taxCalculationService.calculateTax(new Goods[] {
                new Goods("descr", 1.0, 2, null, null),     // base tax = 0.1 * 2 = 0.2
                new Goods("descr", 10.0, 1, true, null),    // tax = 0
                new Goods("descr", 20.0, 1, true, true),    // import tax = 20.0 * 5% = 1
                new Goods("descr", 100.0, 3, null, true),   // import tax = 5%; base tax = 10%; tax = 100 *3 * 15% = 45
        });                                                 // total tax = 45 + 1 + 0.2 = 46.2
        ArgumentCaptor<Double> taxCaptor = ArgumentCaptor.forClass(Double.class);
        verify(roundingServiceMock).round(taxCaptor.capture());
        assertEquals(46.2, taxCaptor.getValue(), 0.0001);
        verify(guessServiceMock, times(2)).guessIfImported(any(Goods.class));
        verify(guessServiceMock, times(2)).guessIfTaxFree(any(Goods.class));
    }
}
