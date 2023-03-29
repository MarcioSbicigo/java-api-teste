import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHttp {
    public String buscaDados (String url){

        try {

            URI apiUrl = URI.create(url);

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpRequest httpRequest = HttpRequest.newBuilder(apiUrl).GET().build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }

    }
}