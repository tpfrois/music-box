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
    public List<Musica> musicas = new ArrayList<>();

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public void cadastrar() throws JAXBException {
        Scanner S = new Scanner(System.in);

        String nome, artista, album, genero;
        int minutos, segundos;

        System.out.println("\nCadastro de Músicas\n");
        System.out.print("Nome: ");
        nome = S.nextLine();

        System.out.print("Artista: ");
        artista = S.nextLine();

        System.out.print("Álbum: ");
        album = S.nextLine();

        System.out.print("Gênero: ");
        genero = S.nextLine();

        System.out.println("DURAÇÃO");
        System.out.print("Minutos: ");
        minutos = S.nextInt();

        System.out.print("Segundos: ");
        segundos = S.nextInt();


        Musica music = new Musica(nome, artista, album, genero, minutos, segundos);

        int ultimoID = this.getMusicas().size();
        music.setId(ultimoID + 1);

        this.getMusicas().add(music);
        this.ordenar();

        XML.salvar(this);

        System.out.print("\nMúsica cadastrada!\nPressione uma tecla para continuar...");
        S.nextLine();
    }

    private void ordenar() {
        // Ordena por nome depois por artista
        Collections.sort(this.getMusicas(), (A, B) -> {
            String nome1 = A.getNome().toLowerCase();
            String nome2 = B.getNome().toLowerCase();
            int sComp = nome1.compareTo(nome2);

            if(sComp != 0){
                return sComp;
            }

            String artista1 = A.getArtista().toLowerCase();
            String artista2 = B.getArtista().toLowerCase();

            return artista1.compareTo(artista2);
        });
    }

    public void listar(){
        Scanner S = new Scanner(System.in);
        System.out.println("\nMÚSICAS CADASTRADAS");
        if(this.getMusicas().size() == 0){
            System.out.println("Não existem músicas cadastradas!");
        } else {
            for(Musica M: this.getMusicas()){
                System.out.printf("\nNome: %1$s\t- Artista: %2$s\t- Album: %3$s\t- Duracao: %4$s\t- Genero: %5$s",
                        M.getNome(), M.getArtista(), M.getAlbum(), M.getDuracao(), M.getGenero());
            }
        }
        System.out.print("\n\nPressione uma tecla para continuar...");
        S.nextLine();
    }

    public Musica buscar(String nome, String artista){
        Musica mus = new Musica();

        String nomeLimpo = nome.toLowerCase();
        String artistaLimpo = artista.toLowerCase();

        for(Musica m : this.getMusicas()){
           if(nomeLimpo.equals(m.getNome().toLowerCase()) && artistaLimpo.equals(m.getArtista().toLowerCase())){
               mus = m;
               break;
           }
        }

        return mus;
    }
}
