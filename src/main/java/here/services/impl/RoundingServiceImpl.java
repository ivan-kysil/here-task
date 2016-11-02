package here.services.impl;

import here.services.RoundingService;
import org.springframework.stereotype.Component;

@Component
public class RoundingServiceImpl implements RoundingService {

    @Override
    /**
     * Rounds up input value to nearest 0.05 amount
     */
    public double round(double input) {
        return Math.ceil(input * 20)/20.0;
    }
}
