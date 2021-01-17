package atividade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author André Luiz Dias de Oliveira
 * Implementar um pequeno protocolo para coletar informações sobre a máquina remota (servidor).
 * As informações devem incluir, Memória total, Memória usada, Memória livre,
 * Número de processadores disponíveis, espaço total do disco principal e espaço livre do disco principal.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Menu:\n" +
                "Digite 1 - Memoria Total\n" +
                "Digite 2 - Memoria Livre\n" +
                "Digite 3 - Memoria Usada\n" +
                "Digite 4 - Numero de Processadores disponiveis\n" +
                "Digite 5 - Espaço total em disco\n" +
                "Digite 6 - Espaço disponivel em disco\n" +
                "Digite 0 - Para sair"
        );
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            String clientChoice = scanner.nextLine();
            if (clientChoice.equals("0")) {
                socket.close();
                break;
            }
            printWriter.println(clientChoice);
            printWriter.flush();
            String serverResponse = bufferedReader.readLine();
            System.out.println(serverResponse);

        }
    }
}
