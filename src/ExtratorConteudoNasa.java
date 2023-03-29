import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorConteudoNasa implements ExtratorConteudo {
    public List<Conteudo> extraiConteudos(String json){

        // extraindo somente os dados que interessam para o app;
        JsonParser jsonParser = new JsonParser();

        List<Map<String, String>> listaAtributosJson = jsonParser.parse(json);
        List<Conteudo> conteudos = new ArrayList<>();

        // populando a lista de conteúdos
        for(Map<String, String> atributos : listaAtributosJson){

            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");

            Conteudo conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
