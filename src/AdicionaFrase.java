import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class AdicionaFrase {
    void criar(String frase, InputStream inputStream, String nomeArquivo, String diretorio) throws Exception {

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria uma nova imagem com transparência e novo tamanho
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        //configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont((fonte));

        // escrever uma frase na nova imagem
        graphics.drawString(frase, 0, novaAltura-100);

        // escrever a nova imagem em um arquivo
        File diretorioImagem = new File(diretorio + "/" + nomeArquivo);
        diretorioImagem.getParentFile().mkdirs();

        ImageIO.write(novaImagem, "png", diretorioImagem);
    }
}
