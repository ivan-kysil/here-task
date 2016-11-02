package here.services;

import org.springframework.stereotype.Component;

@Component
public class RoundingServiceImpl implements RoundingService {

    @Override
    /**
     * Rounds input value to nearest 0.05 amount
     */
    public double round(double input) {
        return Math.round(input * 20)/20.0;
    }
}
