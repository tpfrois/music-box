import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XML {
    static Musicas carregar() throws JAXBException, IOException {
        // CARREGA AS MUSICAS DO XML
        JAXBContext jaxbContext = JAXBContext.newInstance(Musicas.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        String userDir = System.getProperty("user.dir");
        File f = new File(userDir + "\\MinhasMusicas.xml");

        Musicas musicas = new Musicas();
        musicas.setMusicas(new ArrayList<>());

        if(!f.exists()) {
            XML.salvar(musicas);
        } else {
            musicas = (Musicas) jaxbUnmarshaller.unmarshal(f);
        }
        return musicas;
    }

    static void salvar(Musicas musicas) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Musicas.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        String userDir = System.getProperty("user.dir");
        jaxbMarshaller.marshal(musicas, new File(userDir + "\\MinhasMusicas.xml"));
    }
}
