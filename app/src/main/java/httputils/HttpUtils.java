package httputils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bprabhanjan on 12-Feb-16.
 */
public class HttpUtils {

    public String getWeatherForecastData(String BASE_URL) {
        HttpURLConnection httpConnection = null;
        InputStream inputStream = null;

        try {

            String url = BASE_URL;
            httpConnection = (HttpURLConnection) (new URL(url)).openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            StringBuffer dataBuffer = new StringBuffer();
            inputStream = httpConnection.getInputStream();
            BufferedReader dataBufferReader = new BufferedReader(new InputStreamReader(inputStream));
            String row = null;
            while ((row = dataBufferReader.readLine()) != null)
                dataBuffer.append(row + "\r\n");

            inputStream.close();
            httpConnection.disconnect();

            return dataBuffer.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Throwable t) {
            }
            try {
                httpConnection.disconnect();
            } catch (Throwable t) {
            }
        }

        return null;

    }
}
