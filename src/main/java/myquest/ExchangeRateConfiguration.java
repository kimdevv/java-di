package myquest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeRateConfiguration {

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new DaumExchangeRateProvider();
    }

    @Bean
    public ExchangeRateRenderer exchangeRateRenderer() {
        final ExchangeRateRenderer renderer = new StandardOutputExchangeRateRenderer();
        renderer.setExchangeRateProvider(exchangeRateProvider());
        return renderer;
    }
}
