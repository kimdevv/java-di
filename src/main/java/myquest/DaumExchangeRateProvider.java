package myquest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaumExchangeRateProvider implements ExchangeRateProvider {

    private static final String DAUM_EXCHANGES_API_URL = "https://finance.daum.net/api/exchanges/FRX.KRWUSD";
    private static final String DAUM_EXCHANGES_URL = "https://finance.daum.net/exchanges/FRX.KRWUSD";

    @Override
    public double getExchangeRate() {
        final HttpURLConnection httpURLConnection =  connect();
        final String responseBody = readResponseBody(httpURLConnection);
        final var pattern = Pattern.compile("\"basePrice\":(\\d+\\.?\\d*)");
        final Matcher matcher = pattern.matcher(responseBody);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0;
    }

    // Daum API에 연결한다.
    private static HttpURLConnection connect() {
        try {
            URL url = URI.create(DAUM_EXCHANGES_API_URL).toURL();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("referer", DAUM_EXCHANGES_URL);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("HTTP 응답 코드: " + responseCode);
            }
            return httpURLConnection;
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    // 연결한 HTTP 응답의 본문을 읽어온다.
    private static String readResponseBody(HttpURLConnection httpURLConnection) {
        try (final InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            final StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }
}
