import java.io.IOException;
import java.util.*;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "musicas")
@XmlAccessorType (XmlAccessType.FIELD)
public class Musicas {
    @XmlElement(name = "musica")
    private List<Musica> musicas = null;

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public void cadastrar(Musicas musicas) throws JAXBException, IOException {
        Scanner S = new Scanner(System.in);
        String nome, artista, album, duracao, genero;

        System.out.println("\nCadastro de Músicas\n");
        System.out.print("Nome: ");
        nome = S.nextLine();

        System.out.print("Artista: ");
        artista = S.nextLine();

        System.out.print("Álbum: ");
        album = S.nextLine();

        System.out.print("Duração: ");
        duracao = S.nextLine();

        System.out.print("Gênero: ");
        genero = S.nextLine();

        Musica music = new Musica(nome, artista, album, duracao, genero);
        int ultimoID = musicas.getMusicas().size();
        music.setId(ultimoID + 1);

        musicas.getMusicas().add(music);
        XML.salvar(musicas);

        System.out.print("\nMúsica cadastrada!\nPressione uma tecla para continuar...");
        S.nextLine();
    }

    public void listar(Musicas musicas){
        Scanner S = new Scanner(System.in);
        System.out.println("\nMÚSICAS CADASTRADAS");
        if(musicas.getMusicas().size() == 0){
            System.out.println("Não existem músicas cadastradas!");
        } else {
            for(Musica M: musicas.getMusicas()){
                System.out.printf("\nNome: %1$s\t- Artista: %2$s\t- Album: %3$s\t- Duracao: %4$s\t- Genero: %5$s",
                        M.getNome(), M.getArtista(), M.getAlbum(), M.getDuracao(), M.getGenero());
            }
        }
        System.out.print("\n\nPressione uma tecla para continuar...");
        S.nextLine();
    }
}
