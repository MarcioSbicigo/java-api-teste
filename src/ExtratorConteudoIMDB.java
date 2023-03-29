import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoIMDB implements ExtratorConteudo {
    public List<Conteudo> extraiConteudos(String json){

        // extraindo somente os dados que interessam para o app;
        JsonParser jsonParser = new JsonParser();

        List<Map<String, String>> listaAtributosJson = jsonParser.parse(json);
        List<Conteudo> conteudos = new ArrayList<>();

        // populando a lista de conteúdos
        for(Map<String, String> atributos : listaAtributosJson){

            String titulo = atributos.get("title");
            String urlImagemAPI = atributos.get("image");
            String[] urlImagem = urlImagemAPI.split("_");

            Conteudo conteudo = new Conteudo(titulo, urlImagem[0]+"jpg");

            conteudos.add(conteudo);
        }

        return conteudos;
    }

}
