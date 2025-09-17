package myquest;

import java.io.InputStream;
import java.util.Properties;

public class ExchangeRateSupportFactory {

    private final Properties properties;
    private final ExchangeRateProvider exchangeRateProvider;

    private final ExchangeRateRenderer exchangeRateRenderer;

    private ExchangeRateSupportFactory() {
        this.properties = new Properties();
        try {
            // resource 디렉터리에서 myquest.properties 파일을 InputStream으로 가져온다.
            InputStream resourceStream = this.getClass().getResourceAsStream("/myquest.properties");

            // 불러온 파일을 properties 객체에 저장한다.
            properties.load(resourceStream);

            // myquest.properties 파일에서 provider.class와 renderer.class 값을 가져온다.
            // providerClass, rendererClass의 값은 객체로 만들 클래스 이름이다.
            String providerClass = properties.getProperty("provider.class");
            String rendererClass = properties.getProperty("renderer.class");

            // 클래스 이름으로 ExchangeRateProvider와 ExchangeRateRenderer 객체를 생성한다.
            this.exchangeRateProvider = (ExchangeRateProvider) Class.forName(providerClass).getDeclaredConstructor().newInstance();
            this.exchangeRateRenderer = (ExchangeRateRenderer) Class.forName(rendererClass).getDeclaredConstructor().newInstance();

            // 객체를 연결한다.
            exchangeRateRenderer.setExchangeRateProvider(exchangeRateProvider);
        } catch (Exception excpetion) {
            throw new RuntimeException(excpetion);
        }
    }

    public static ExchangeRateSupportFactory getInstance() {
        return new ExchangeRateSupportFactory();
    }

    public ExchangeRateRenderer getExchangeRateRenderer() {
        return exchangeRateRenderer;
    }
}
