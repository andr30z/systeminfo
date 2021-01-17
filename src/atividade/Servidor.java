package atividade;


import com.sun.management.OperatingSystemMXBean;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author André Luiz Dias de Oliveira
 */
public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        System.out.println(socket.getInetAddress() + " Conectado!");
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        while (true) {
            String clientResponse = bufferedReader.readLine();
            if (clientResponse == null)
                break;
            printWriter.println(doSwitch(clientResponse));
            printWriter.flush();
        }
        serverSocket.close();
    }


    public static String doSwitch(String response) {
        switch (response) {
            case "1":
                return getSystemMemoryInfo(1);
            case "2":
                return getSystemMemoryInfo(2);
            case "3":
                return getSystemMemoryInfo(3);
            case "4":
                return String.valueOf(ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors()) + " processadores";
            case "5":
                return convertResponse(new File("/").getTotalSpace());
            case "6":
                return  convertResponse(new File("/").getFreeSpace());
            default:
                return "Informe um valor valido!";

        }
    }

    public static String convertResponse(long response) {
        double converted = (double) response / 1024 / 1024 / 1024;
        return converted +" GB";
    }

    public static String getSystemMemoryInfo(int memoryType) {
        OperatingSystemMXBean os = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long memory = 0;
        if (memoryType == 1)
            memory = os.getTotalPhysicalMemorySize();
        else if (memoryType == 2)
            memory = os.getFreePhysicalMemorySize();
        else
            memory = os.getTotalPhysicalMemorySize() - os.getFreePhysicalMemorySize();
        return convertResponse(memory);
    }

}
