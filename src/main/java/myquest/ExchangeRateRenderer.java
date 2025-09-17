package myquest;

public interface ExchangeRateRenderer {

    void render();
    void setExchangeRateProvider(ExchangeRateProvider exchangeRateProvider);
    ExchangeRateProvider getExchangeRateProvider();
}
