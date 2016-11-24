
import java.io.*;
import java.net.*;

public class Client {
    public void run() {
        try {
            /**
             * Клиент предполагает, что сервер ожидает запроса на подключение на serverPort порт через TCP.
             */
            int serverPort = 4021;
            InetAddress host = InetAddress.getByName("localhost");
            System.out.println("Connecting to server on port " + serverPort);

            Socket socket = new Socket(host,serverPort);
            //Socket socket = new Socket("127.0.0.1", serverPort);
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());
            /**
             * Когда запрос клиента принят,
             * клиент создает входной поток для приема данных из сокета,
             * а выходной поток для передачи данных к разъему на конце сервера канала.
             */
            PrintWriter toServer =
                    new PrintWriter(socket.getOutputStream(),true); //true - автоматический сброс буферов
            BufferedReader fromServer =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            toServer.println("Hello from " + socket.getLocalSocketAddress());
            String line = fromServer.readLine();
            System.out.println("Client received: " + line + " from Server");

            toServer.close();
            fromServer.close();
            socket.close();
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        /**
         * Socket бросает ioexception, если он не может сделать соединение
         */
        catch(IOException e){
            e.printStackTrace();
        }
    }


}