import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        // estabelecendo uma conexão http para obter os dados da api
        String url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbmostpopularmovies.json";

        URI apiUrl = URI.create(url);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(apiUrl).GET().build();
        HttpResponse<String> response = httpClient.send(httpRequest, BodyHandlers.ofString());

        String body = response.body();

        // extraindo somente os dados que interessam para o app (titulo, poster, classificação)
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);

        // baixa as 10 primeiras capas de filmes
        int count = 1;

        for (Map<String, String> filme : listaDeFilmes) {

            //Obtendo url da imagem e tratando para mudar a resulução final
            String imageUrlFromApi = filme.get("image");
            String[] urlImage = imageUrlFromApi.split("_");
            String finalUrlImage = urlImage[0]+"jpg";

            InputStream inputStream = new URL(urlImage[0]).openStream();

            //Definindo nome do arquivo final
            String nomeArquivo = "Top " + count + " - " + filme.get("title").replace(":", " -") + (".jpg");

            //Criando sticker com frase personalizada
            StickerGen sticker = new StickerGen();
            sticker.criar(inputStream, nomeArquivo, "Filme bão!");

            System.out.println(filme.get("title") + " baixado!");

            count ++;
            if(count > 10) break;
        }

    }
}