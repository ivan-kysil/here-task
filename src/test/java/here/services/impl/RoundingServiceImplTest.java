package here.services.impl;

import org.junit.Assert;
import org.junit.Test;


public class RoundingServiceImplTest {
    private RoundingServiceImpl service = new RoundingServiceImpl();

    @Test
    public void test() {
        Assert.assertEquals(1.00, service.round(1.00), 0);
        Assert.assertEquals(1.05, service.round(1.01), 0);
        Assert.assertEquals(1.05, service.round(1.04999), 0);
        Assert.assertEquals(1.05, service.round(1.05), 0);
        Assert.assertEquals(1.10, service.round(1.050001), 0);
        Assert.assertEquals(6.70, service.round(6.66), 0);
    }
}
