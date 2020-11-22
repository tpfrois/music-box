import com.sun.deploy.net.MessageHeader;

import java.util.*;
import java.io.IOException;
import javax.xml.bind.JAXBException;

public class Main {

    public static Queue<Musica> filaMusicas = new LinkedList<>();

    public static void main(String[] args) throws JAXBException, IOException, InterruptedException {
        Scanner S = new Scanner(System.in);

        Musicas musicas = XML.carregar();
        musicas.setMusicas(musicas.getMusicas());

        int opcao = -1;
        do{
            System.out.println("\n+===============================+");
            System.out.println("|             MUSIC BOX         |");
            System.out.println("+===============================+");
            System.out.println("|-------------TOCAR-------------|");
            System.out.println("| 1 - Adicionar música na fila  |");
            System.out.println("| 2 - Listar Fila de músicas    |");
            System.out.println("| 3 - Tocar músicas da fila     |");
            System.out.println("|------------CADASTRO-----------|");
            System.out.println("| 4 - Cadastrar música          |");
            System.out.println("| 5 - Listar músicas cadastradas|");
            System.out.println("|-------------------------------|");
            System.out.println("| 0 - Sair                      |");
            System.out.println("+===============================+");

            try {
                System.out.print("Digite uma opção: ");
                opcao = S.nextInt();
            } catch(InputMismatchException inputMismatchException) {
                System.err.println("A opção deve ser um número entre 0-5!\n");
                S.nextLine();
                continue;
            }

            System.out.println();

            switch(opcao) {
                case 1:
                    adicionarFila(musicas);
                    break;
                case 2:
                    listarFila();
                    break;
                case 3:
                    play(filaMusicas.poll());
                    break;
                case 4:
                    musicas.cadastrar();
                    break;
                case 5:
                    musicas.listar();
                    break;
                case 0:
                    break;
                default:
                    System.err.println("\nOpção Inválida!\n");
                    break;
            }
        } while(opcao != 0);
    }

     public static void adicionarFila(Musicas musicas){
        Scanner S = new Scanner(System.in);

        System.out.println("\nAdicionar música na fila para tocar");

        System.out.print("\nNome da Música: ");
        String nome = S.nextLine();
        System.out.print("Nome do Artista: ");
        String artista = S.nextLine();

        Musica m = musicas.buscar(nome, artista);

        if(m.getNome() == null) {
            System.err.println("\nMúsica não encontrada! Pressione uma tecla para continuar...");
            S.nextLine();
            return;
        } else {
            filaMusicas.add(m);
            System.out.println("\nMúsica adicionada: ");
            System.out.printf("\n%1$s - %2$s - %3$s", m.getNome(), m.getArtista(), m.getDuracao());
            System.out.print("\nPressione uma tecla para continuar...");
            S.nextLine();
        }
    }

    public static void listarFila(){
        Scanner S = new Scanner(System.in);
        if(filaMusicas.size() == 0){
            System.err.print("\nA fila está vazia! Pressione uma tecla para continuar...");
            S.nextLine();
        } else {
            System.out.println("\nFILA DAS MÚSICAS À TOCAR:");
            for(Musica m : filaMusicas){
                System.out.println(m.getNome() + " - " + m.getArtista());
            }
            System.out.print("\nPressione uma tecla para continuar...");
            S.nextLine();
        }
    }

    public static void play(Musica m) throws InterruptedException {
        Scanner S = new Scanner(System.in);

        if(m == null){
            System.err.println("\nA fila está vazia! Adicione músicas para tocar.");
            System.err.print("Pressione uma tecla para continuar...");
            S.nextLine();
            return;
        }

        int duracaoEmMS = (m.getMinutos() * 60 * 1000) + (m.getSegundos() * 1000);
        int currentMS = 0;

        while(currentMS <= duracaoEmMS){
            int segundos = (int) ((currentMS / 1000) % 60);
            int minutos = (int) ((currentMS / 1000) / 60);
            System.out.printf("\rTocando %1$s - %2$s! %3$dm %4$ds/%5$dm %6$ds",
                    m.getNome(), m.getArtista(), minutos, segundos, m.getMinutos(), m.getSegundos());
            Thread.sleep(1000);
            currentMS += 1000;
        }

        System.out.print("\n\nTocar próxima música? [S/N]: ");
        char opcao = S.next().toUpperCase().charAt(0);
        if(opcao == 'S'){
            play(filaMusicas.poll());
        }
    }

}
