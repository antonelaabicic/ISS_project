package dhmz.service;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class WeatherServiceImpl implements WeatherService {

    private static final String XML_URL = "https://vrijeme.hr/hrvatska_n.xml";

    @Override
    public List<String> getTemperature(String city) {
        List<String> results = new ArrayList<>();

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(XML_URL))
                    .GET()
                    .build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(response.body());

            NodeList gradNodes = doc.getElementsByTagName("Grad");

            for (int i = 0; i < gradNodes.getLength(); i++) {
                Element grad = (Element) gradNodes.item(i);

                String name = grad.getElementsByTagName("GradIme").item(0).getTextContent();
                String temp = grad.getElementsByTagName("Temp").item(0).getTextContent().trim();

                if (name.toLowerCase().contains(city.toLowerCase())) {
                    results.add(name + ": " + temp + " °C");
                }
            }

            if (results.isEmpty()) {
                results.add("No cities match: " + city);
            }

        } catch (Exception e) {
            results.add("Error: " + e.getMessage());
        }

        return results;
    }
}
