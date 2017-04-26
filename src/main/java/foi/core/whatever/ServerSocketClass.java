package foi.core.whatever;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerSocketClass implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		
		String fromclient;

		ServerSocket server = new ServerSocket (5000);

		System.out.println ("TCPServer Waiting for client on port 5000");

		while(true) 
		{
		    Socket connected = server.accept();
		    System.out.println( " THE CLIENT"+" "+ connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");

		    BufferedReader inFromClient = new BufferedReader(new InputStreamReader (connected.getInputStream()));

		    while ( true )
		    {
		        fromclient = inFromClient.readLine();

		        if ( fromclient.equals("q") || fromclient.equals("Q") )
		        {
		            connected.close();
		            break;
		        }
		        else
		        {
		            System.out.println( "RECIEVED:" + fromclient );
		        } 
		    }
		}

	}

	

}
