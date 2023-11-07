package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {

    Socket socket;
    BufferedReader br;
    PrintWriter out;

    Client() {

        try {
            System.out.println("Client is ready to running");
            socket = new Socket("192.168.59.134", 7777);
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            starRedding();
            starWriting();
        } catch (Exception e) {

            System.out.println(e.getStackTrace() );


        }
    }

    public void starRedding()
    {
        System.out.println("Start Reading for data Server");

        Runnable r1= ()->{

            try{
                while (true){

                    String h = br.readLine();
                    if (h.equalsIgnoreCase("End")){
                        System.out.println("Server End tha chat");
socket.close();
                        break;}


                    System.out.println("Server : " + h);

                }
            }
            catch (Exception e)
            {
                System.out.println(e.getStackTrace());



            }





        };
        new Thread(r1).start();

    }

    public void starWriting()
    {
        System.out.println("Start Writing for data to Server");

        Runnable r2 = ()->{

            try {
                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                String p = br1.readLine();
                out.println(p);
                out.flush();
            }
            catch (Exception e)
            {
                System.out.println(e.getStackTrace());
            }

        };
        new Thread(r2).start();



    }


    public static void main(String[] args) {

        Client myServer = new Client();

    }
}