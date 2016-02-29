import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Client implements Runnable{
	
	public static Socket clientSocket = new Socket();
	public static String clientMessage = new String(); 
	static Scanner inS ;
	static PrintWriter outPW;
	//public static String hostName;
	//public static int portNumber;
	
	public Client (Socket X)
	{
		this.clientSocket = X;
	}
	public static void main(String[] args)
	{
		String hostName = "127.0.0.1";
        int portNumber = 2048;  
        
        clientMessage = "Prompted when start running"; 
        Client_GUI.main(null);
      
        System.out.println("About to enter try");
}
	
	public static void SEND (String message)
	{
		outPW.println(Client_GUI.userName + ": " + message);
		outPW.flush();
		Client_GUI.inputTextArea.setText("");
	}
	
	public static void DISCONNECT() throws IOException
	{
		outPW.println(Client_GUI.userName + "has disconnected...");
		outPW.flush();
		clientSocket.close();
		JOptionPane.showMessageDialog(null, "You have been disconnected...");
		System.exit(0);
	}

	public void run()
	{
		//if()192.168.0.22
		
		//try{

        //while(true)
        {
       
        	try
        	{
        		System.out.println("Entering try in Client");
	        		 ////create socket with ip address & port number
	        		//clientSocket = new Socket(hostName,portNumber);//establish socket 
	        		//Server.ConnectionList.add(clientSocket);
	                
	               System.out.println("Socket created");
        		  	
	               try{
	        			////send message to server
	        			inS = new Scanner(clientSocket.getInputStream());//send message to server here        		
	        			outPW = new PrintWriter(clientSocket.getOutputStream());
	        			outPW.flush();
	        			
	        			while(true)
	        			{
		        				if(inS.hasNext())
								{
									System.out.println("getting user name");
									clientMessage = inS.nextLine();
									
									
									if(clientMessage.contains("#?!"))								
									{
										String temp = clientMessage.substring(3);
										//get rid of delimiters taken by scanner
										temp = temp.replace("[", " ");
										temp = temp.replace("]", " ");
										System.out.println("triming username");
										
										String [] currentUser = clientMessage.split(", "); 
										System.out.println("Setting list");
										Client_GUI.onlineLT.setListData(currentUser);
										System.out.println("List set");
									}
									else
									{
										System.out.println("getting message and appending to chatbox");
										Client_GUI.conversationTextArea.append(clientMessage + "\n");
									
										if(Client_GUI.img!=null)
										{
											Client_GUI.pictureSend();
										}
										Client_GUI.img = null;

									}	
									
									//show messages on the conversationPane with the user's name attached
									//if(!clientMessage.contains("#?!"))
									
								}	
							
								
	        			}
	        			
	               }
	               finally
       			{
       				clientSocket.close();
       			}
        					
        	}
        
        	catch(Exception e) 
        	{
        		// if there is no input
        		System.out.println();
        	}
        }
        
		//}
		

}
	
	public static void SocketReturn(Socket X)
	{
		clientSocket = X;
		
	}
	public static Socket Return(Socket x)
	{
		return clientSocket;
	}

	
}
