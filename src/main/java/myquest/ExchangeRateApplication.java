package myquest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExchangeRateApplication {

    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("myquest.xml");
        final ExchangeRateRenderer renderer = context.getBean("exchangeRateRenderer", ExchangeRateRenderer.class);
        renderer.render();
    }
}
