package myquest;

public class StandardOutputExchangeRateRenderer implements ExchangeRateRenderer{

    private ExchangeRateProvider exchangeRateProvider;

    @Override
    public void render() {
        System.out.printf("1달러 환율: %.2f원%n", exchangeRateProvider.getExchangeRate());
    }

    @Override
    public void setExchangeRateProvider(ExchangeRateProvider exchangeRateProvider) {
        this.exchangeRateProvider = exchangeRateProvider;
    }

    @Override
    public ExchangeRateProvider getExchangeRateProvider() {
        return exchangeRateProvider;
    }
}
