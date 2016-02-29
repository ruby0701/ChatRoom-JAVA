import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;



public class Server{
	
	public static ArrayList<Socket> ConnectionList = new ArrayList<Socket>();
	//hold all the connections, to echos out the message to every user
	public static ArrayList<String> UserList = new ArrayList<String>();
	//record all users and pass to JList to show
	public static int count=0;
	public static Socket clientSOCK;
	private static Scanner input;
	private static PrintWriter output;
	
	
	public static void main(String[] args) throws Exception
	{
		try{
				try{
						int portNumber = 2048;
						
						////create server socket and connect to client socket by accept()
						ServerSocket serverSocket = new ServerSocket(portNumber);//create socket and establish connection
						//only need to create once
						
						while(true)
						{
							System.out.println("Entering while");
							////accept connection from clients
								clientSOCK = serverSocket.accept();//connection confirmed
								ConnectionList.add(clientSOCK);
								System.out.println("socket accepted");
								
							////add to connection list
								Add_User_Name(clientSOCK);
								
							////getting input and output
								input = new Scanner(clientSOCK.getInputStream());
								output = new PrintWriter(clientSOCK.getOutputStream());
								output.flush();
								
							////create thread and use multi-thread to respond to every client
								Client_GUI temp = new Client_GUI(clientSOCK);
								Thread multiSocket = new Thread(temp);
								multiSocket.start();
								System.out.println("start runnable");
								

								if(Client_GUI.img!=null)
								{
									Client_GUI.pictureSend();
								}
								Client_GUI.img = null;
								
						}//while
						
				}//inner try
				
				finally
				{	
				}
				
			}//outer try
		finally
		{
		}
		
}
	
	private static void Add_User_Name(Socket client) throws IOException 
	{
		Scanner input = new Scanner(client.getInputStream());
		String userName = input.nextLine();
		UserList.add(userName);
		//client.getInputStream()??
		
		for(int i=1;i<=ConnectionList.size();i++)
		{
			//for(int j=1;j<=UserList.size();j++)
			{
				//String temp = new String();
				Socket temp = ConnectionList.get(i-1);
				PrintWriter out = new PrintWriter(temp.getOutputStream());
				out.println( "#?!" + UserList);
				out.flush();
				
				//String[] templist = new String[1000];		
				//templist[j]=temp;
				//Client_GUI.onlineLT.setListData(templist);;
				//adding string to JList onlineList
			}		
		}
		
	}
}
