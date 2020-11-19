import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

@XmlRootElement(name = "musica")
@XmlAccessorType (XmlAccessType.FIELD)
public class Musica {
    private long ID;
    private String Nome;
    private String Artista;
    private String Album;
    private String Duracao;
    private String Genero;

    public Musica() {
    }

    public Musica(String nome, String artista, String album, String duracao, String genero){
        this.Nome = nome;
        this.Artista = artista;
        this.Album = album;
        this.Duracao = duracao;
        this.Genero = genero;
    }

    public long getId() { return this.ID; }
    public void setId(long id) { this.ID = id;}

    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getArtista() {
        return Artista;
    }
    public void setArtista(String artista) {
        this.Artista = artista;
    }

    public String getAlbum() {
        return Album;
    }
    public void setAlbum(String album) {
        this.Album = album;
    }

    public String getDuracao() {
        return Duracao;
    }
    public void setDuracao(String duracao) {
        this.Duracao = duracao;
    }

    public String getGenero() {
        return Genero;
    }
    public void setGenero(String genero) {
        this.Genero = genero;
    }
}
