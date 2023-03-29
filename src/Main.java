import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        // Url's das api's utilizadas
        String urlNasa = "https://api.nasa.gov/planetary/apod?api_key=gWdT8Psr4ATuPwRFsEulvkdvWqK9hGm7o6Uq1DoL&start_date=2023-01-01&end_date=2023-01-10";
        String urlIMDB = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbtop250moviesdata.json";

        // Criando o cliente HTTP e fazendo a chamada do método buscaDados(), que retorna o body.
        // Após chamar o método, armazena os jsons em uma string
        ClienteHttp ClienteHttp = new ClienteHttp();

        String jsonNasa = ClienteHttp.buscaDados(urlNasa);
        String jsonIMDB = ClienteHttp.buscaDados(urlIMDB);

        // Criando os extratores para cada json
        ExtratorConteudo extratorNasa = new ExtratorConteudoNasa();
        ExtratorConteudo extratorIMDB = new ExtratorConteudoIMDB();

        // Armazenando os dados em Lists
        List<Conteudo> conteudosNasa = extratorNasa.extraiConteudos(jsonNasa);
        List<Conteudo> conteudosIMDB = extratorIMDB.extraiConteudos(jsonIMDB);

        // Manipulando e exibindo os dados obtidos
        for (int i = 1; i <= 10; i++) {

            Conteudo conteudoNasa = conteudosNasa.get(i-1);
            Conteudo conteudoIMDB = conteudosIMDB.get(i-1);

            InputStream inputStreamNasa = new URL(conteudoNasa.getUrlImagem()).openStream();
            InputStream inputStreamIMDB = new URL(conteudoIMDB.getUrlImagem()).openStream();

            // definindo nome do arquivo final
            String nomeArquivoNasa = conteudoNasa.getTitulo().replace(":", " -").replace("\\", "") + ".jpg";
            String nomeArquivoIMDB = "Top " + i + " - " + conteudoIMDB.getTitulo().replace(":", " -") + (".jpg");

            // Adicionando uma frase personalizada abaixo da imagem
            AdicionaFrase stickerNasa = new AdicionaFrase();
            stickerNasa.criar("[Qualquer frase]", inputStreamNasa, nomeArquivoNasa, "saida/api-nasa");

            AdicionaFrase stickerIMDB = new AdicionaFrase();
            stickerIMDB.criar("[Qualquer frase]", inputStreamIMDB, nomeArquivoIMDB, "saida/api-imdb");

            System.out.println(conteudoNasa.getTitulo() + " baixado!");
            System.out.println(conteudoIMDB.getTitulo() + " baixado!");

        }

    }
}