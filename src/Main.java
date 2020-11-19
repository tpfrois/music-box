import java.util.*;
import java.io.IOException;
import javax.xml.bind.JAXBException;

public class Main {
    
    public static void main(String[] args) throws JAXBException, IOException {
        Scanner leitor = new Scanner(System.in);
        Musicas musicas = XML.carregar();

        Queue<Musica> filaMusicas = new LinkedList<>();
        int opcao;
        do{
            System.out.println("\n+===============================+");
            System.out.println("|             MUSIC BOX         |");
            System.out.println("+===============================+");
            System.out.println("|-------------TOCAR-------------|");
            System.out.println("| 1 - Adicionar música pra tocar|");
            System.out.println("| 2 - Listar Fila de músicas    |");
            System.out.println("|------------CADASTRO-----------|");
            System.out.println("| 3 - Cadastrar música          |");
            System.out.println("| 4 - Listar músicas cadastradas|");
            System.out.println("|-------------------------------|");
            System.out.println("| 0 - Sair                      |");
            System.out.println("+===============================+");
            System.out.print("Digite uma opção: ");
            opcao = leitor.nextInt();

            System.out.println();

            switch(opcao) {
                case 1:
                    Musica e = new Musica("oi", "ola", "tudo", "bem", "?");
                    filaMusicas.add(e);
                    break;
                case 2:
                    listarFila(filaMusicas);
                    break;
                case 3:
                    musicas.cadastrar(musicas);
                    break;
                case 4:
                    musicas.listar(musicas);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção Inválida!\n");
                    break;
            }
        } while(opcao != 0);
    }

    static void adicionarFila(){
        /* TODO: MÉTODO PARA ADICIONAR A FILA
         DEVERA PERGUNTAR QUAL MÚSICA
         BUSCAR - PELO NOME E ARTISTA
                - PELO ID
         */
    }

    static void listarFila(Queue<Musica> q){
        for(Musica m : q){
            System.out.println(m.getNome() + " - " + m.getArtista());
        }
    }

}
