package here.services;

import org.junit.Assert;
import org.junit.Test;


public class RoundingServiceImplTest {
    private RoundingServiceImpl service = new RoundingServiceImpl();

    @Test
    public void test() {
        Assert.assertEquals(1.00, service.round(1.00), 0);
        Assert.assertEquals(1.00, service.round(1.024999), 0);
        Assert.assertEquals(1.05, service.round(1.025000), 0);
        Assert.assertEquals(1.05, service.round(1.05), 0);
    }
}
