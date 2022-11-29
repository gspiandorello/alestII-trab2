import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Aplicacao {

    public void inicia(){

        Scanner in = new Scanner(System.in);
        String opcao;

        do {
            apresentaMenu();
            opcao = in.nextLine();
            String opcaoEscolhida = "";

            switch (opcao) {
                case "0":
                    break;
                case "1":
                    opcaoEscolhida = "casocohen10.txt";
                    break;
                case "2":
                    opcaoEscolhida = "casocohen20.txt";
                    break;
                case "3":
                    opcaoEscolhida = "casocohen30.txt";
                    break;
                case "4":
                    opcaoEscolhida = "casocohen40.txt";
                    break;
                case "5":
                    opcaoEscolhida = "casocohen50.txt";
                    break;
                case "6":
                    opcaoEscolhida = "casocohen60.txt";
                    break;
                default:
                    System.out.println("Opcao invalida! Por favor digite novamente.");
                    System.out.println("");
                    break;
            }

            try{
                leArquivoTexto("casos_cohen_t2/" + opcaoEscolhida);;
            } catch (IOException e){

            }


        } while(!opcao.equals("0"));
    }

    public void apresentaMenu(){
        System.out.println("BEM VINDO A SORVETERIA DOS HORRORES HAHAHAHAAHAHAHAHA");
        System.out.println("Digite 1 para ler o arquivo de teste casocohen10.txt");
        System.out.println("Digite 2 para ler o arquivo de teste casocohen20.txt");
        System.out.println("Digite 3 para ler o arquivo de teste casocohen30.txt");
        System.out.println("Digite 4 para ler o arquivo de teste casocohen40.txt");
        System.out.println("Digite 5 para ler o arquivo de teste casocohen50.txt");
        System.out.println("Digite 6 para ler o arquivo de teste casocohen60.txt");
        System.out.println("Digite 0 para encerrar o programa :(");
    }

    public void leArquivoTexto(String nomeArquivo) throws IOException {
        Grafo grafo = new Grafo();
        Path path = Paths.get(nomeArquivo);
        BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
        String line = null;
        while(!Objects.equals(line = reader.readLine(), null)){
            if(!line.contains("->")){
                break;
            }
            String[] temp = line.split("->");
            temp[0] = temp[0].replaceAll(" ", "");
            temp[1] = temp[1].replaceAll(" ", "");
            String primeiroSabor = temp[0];
            String segundoSabor = temp[1];

            Grafo.Nodo sabor = grafo.procuraSabor(primeiroSabor);

            if(sabor == null){
                sabor = grafo.criaSabor(primeiroSabor);
            }

            Grafo.Nodo saborMaisForte = grafo.procuraSabor(segundoSabor);

            if(saborMaisForte == null){
                saborMaisForte = grafo.criaSabor(segundoSabor);
            }

            grafo.adicionaSaborMaisForte(sabor, saborMaisForte);
        }

        grafo.adicionaSaboresMaisFortes();

        System.out.println("Quantidade de copos com 2 sabores: " + grafo.possiveisCoposComDoisSabores());

        System.out.println("Quantidade de copos com 3 sabores: " + grafo.possiveisCoposComTresSabores());

        System.out.println("=====================================================");
        System.out.println("");
    }

}
