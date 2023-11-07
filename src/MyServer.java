package src;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter out;

   //Constructor
    public MyServer() {

     try {
         System.out.println("Server is ready to running");
         System.out.println("Waiting...");
         server = new ServerSocket(7777);
         socket =  server.accept();
         br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         out = new PrintWriter(socket.getOutputStream());
           starRedding();
           starWriting();

     }
     catch (Exception e) {
         System.out.println(e.getStackTrace());
         System.out.println("End");

     }}

     public void starRedding()
        {
            System.out.println("Start Reading for data Clint");

            Runnable r1= ()->{

                try{
                    while (true){

                    String h = br.readLine();
                    if (h.equalsIgnoreCase("End")){
                        System.out.println("Clint End tha chat");
                        server.close();
                        break;}


                        System.out.println("Clint : " + h);

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
       System.out.println("Start Writing for data to Clint");

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

       MyServer myServer = new MyServer();


    }
}
