import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Client_GUI implements Runnable{
	
		 //Global - implemented later
		static String userName = "Anonymous";
		public static Socket clientSOCK;
		public static Client SOCK;
		private static Scanner inS;
		private static PrintWriter outPW;
		private static int width=0;
		private static int height=0;
		public static BufferedImage img = null;
		
		//----------------------------------------------------
		public Client_GUI (Socket X)
		{
			this.clientSOCK = X;
		}
		
		//Main Frame GUI
		
		public static JFrame mainFrame = new JFrame();//temp, add user'sname later
		private static JButton loginBtn = new JButton();
		private static JButton disconnectBtn = new JButton();
		private static JButton sendBtn = new JButton();
		public static JButton attachmentBtn = new JButton();
		private static JLabel infoLabel = new JLabel();
		private static JLabel onlineLabel = new JLabel();
		private static JLabel timeLabel = new JLabel();//
		private static JLabel conversationLabel = new JLabel();
		private static JLabel typeAreaLabel = new JLabel();
		public static JTextArea inputTextArea = new JTextArea();
		public static JTextArea conversationTextArea = new JTextArea();
		private static JScrollPane conversationSP = new JScrollPane();
		private static JScrollPane onlineSP = new JScrollPane();
		static DefaultListModel name = new DefaultListModel();
		public static JList onlineLT = new JList(name);
		
		//Login Frame GUI
		public static JFrame loginFrame = new JFrame();
		private static JTextField nameInput = new JTextField(20);
		private static JButton enterBtn = new JButton("Login");
		private static JLabel nameLable = new JLabel("User Name");
		private static JTextField IPAddInput = new JTextField(20);
		private static JTextField portNumInput = new JTextField(20);
		private static JLabel IPLabel = new JLabel();
		private static JLabel portLabel = new JLabel();
		private static JLabel buffer = new JLabel();
		
		//image file access
		public static JLabel pic = new JLabel();
		//--------------------------------------------------------------
		
		public static void main(String[] args)
		{
			BuildMainWindow();
			Initiate();
		}
		
		private static void Initiate() {
			sendBtn.setEnabled(false);
			disconnectBtn.setEnabled(false);
			attachmentBtn.setEnabled(false);
			loginBtn.setEnabled(true);
			
		}

		//// start building main window /////////////////////////////////////////////////////////////////////
		private static void BuildMainWindow() {
			
			mainFrame.setLocation(250,100);
			mainFrame.setResizable(false);
			ConfigureMainFrame();
			MainFrameAction();
			mainFrame.setVisible(true);
		}


		private static void ConfigureMainFrame() {
			
			//Main Frame 
			Font font = new Font("Consolas", Font.PLAIN, 14); 
			Font font2 = new Font("Consolas", Font.BOLD, 14); 
			mainFrame.setTitle(userName + "'s Chat Box");
			mainFrame.setFont(font);
			mainFrame.getContentPane().setBackground(new java.awt.Color(198,203,221));
			//mainFrame.setBackground(new java.awt.Color(42,50,75));
			//mainFrame.setForeground(new java.awt.Color(42,50,75));
			mainFrame.setSize(800,600);
			mainFrame.getContentPane().setLayout(null);

			//Login Button
			loginBtn.setBackground(new java.awt.Color(85,73,113));
			loginBtn.setForeground(new java.awt.Color(255,255,255));
			loginBtn.setText("LOGIN");
			loginBtn.setFont(font2);
			loginBtn.setBounds(600,473,200,50);//set x,y,width,height
			mainFrame.getContentPane().add(loginBtn);
			
			
			//Disconnect Button
			disconnectBtn.setBackground(new java.awt.Color(85,73,113));
			disconnectBtn.setForeground(new java.awt.Color(255,255,255));
			disconnectBtn.setFont(font2);
			disconnectBtn.setText("Disconnect");
			disconnectBtn.setBounds(600,523,200,50);//set x,y,width,height
			mainFrame.getContentPane().add(disconnectBtn);
			
			
			//Send Button
			sendBtn.setBackground(new java.awt.Color(85,73,113));
			sendBtn.setForeground(new java.awt.Color(255,255,255));
			sendBtn.setFont(font2);
			sendBtn.setText("SEND");
			sendBtn.setBounds(0,533,600,40);//set x,y,width,height
			mainFrame.getContentPane().add(sendBtn);
			
			
			//Attachment Button - attach picture using this button
			attachmentBtn.setBackground(new java.awt.Color(85,73,113));
			attachmentBtn.setForeground(new java.awt.Color(255,255,255));
			attachmentBtn.setText("Attach");
			attachmentBtn.setFont(font);
			attachmentBtn.setBounds(495,408,100,40);
			mainFrame.getContentPane().add(attachmentBtn);
			
			//Type Area Label
			typeAreaLabel.setForeground(new java.awt.Color(42,50,75));
			typeAreaLabel.setText("Message : ");
			typeAreaLabel.setFont(font);
			typeAreaLabel.setBounds(5,390,100,20);//set x,y,width,height
			mainFrame.getContentPane().add(typeAreaLabel);
			
			
			//Text Area - where you type in messages
			inputTextArea.setBackground(new java.awt.Color(198,203,221));
			inputTextArea.requestFocus();
			inputTextArea.setEditable(true);
			inputTextArea.setBounds(5,408,590,120);//set x,y,width,height
			mainFrame.getContentPane().add(inputTextArea);
						
			
			//Conversation Label
			conversationLabel.setBackground(new java.awt.Color(34,34,59));
			conversationLabel.setText("Conversation : ");
			conversationLabel.setFont(font);
			conversationLabel.setBounds(2,2,200,20);
			mainFrame.getContentPane().add(conversationLabel);
			
			
			//Conversation Text Area - where the messages entered are shown
			conversationTextArea.setColumns(25);
			conversationTextArea.setFont(new java.awt.Font("Consolos",10,14));
			conversationTextArea.setBackground(new java.awt.Color(225,229,238));
			conversationTextArea.setForeground(new java.awt.Color(42,50,75));
			conversationTextArea.setLineWrap(true);
			conversationTextArea.setEditable(false);
			conversationTextArea.setBounds(0,0,600,390);
			mainFrame.getContentPane().add(conversationTextArea);
			
			
			//Conversation Scroll Pane - so that you can scroll up to see former messages
			conversationSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			conversationSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			conversationSP.setViewportView(conversationTextArea);
			conversationSP.setBounds(0,0,600,390);//set x,y,width,height
			mainFrame.getContentPane().add(conversationSP);
			
			
			//Online Label
			onlineLabel.setText("Currently Online : ");
			onlineLabel.setFont(font);
			onlineLabel.setToolTipText("");
			onlineLabel.setBounds(600,200,200,20);//set x,y,width,height
			mainFrame.getContentPane().add(onlineLabel);
			
			
			//Online User List
			//String[] tempUserList = {"temp1","temp2"};
			onlineLT.setForeground(new java.awt.Color(34,34,59));
			//onlineLT.setListData(tempUserList);
			//onlineLT.setListData(Client.userName);
			
			//Online User Scroll Pane
			onlineSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			onlineSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			onlineSP.setViewportView(onlineLT);
			onlineLabel.setBackground(new java.awt.Color(199,204,219));
			onlineLabel.setForeground(new java.awt.Color(34,34,59));
			onlineSP.setBounds(600,220,200,250);//set x,y,width,height
			mainFrame.getContentPane().add(onlineSP);
			
			
			//Information Label - shows user information here = "login as XXXX"
			infoLabel.setBackground(new java.awt.Color(199,204,219));
			infoLabel.setForeground(new java.awt.Color(34,34,59));
			infoLabel.setText("Logged in as " + userName);
			infoLabel.setFont(font);
			infoLabel.setBounds(605,0,200,40);
			mainFrame.getContentPane().add(infoLabel);
			
			//Time Label - to tell the user current time
			timeLabel.setBackground(new java.awt.Color(199,204,219));
			timeLabel.setForeground(new java.awt.Color(34,34,59));
			//LocalDateTime time = new LocalDateTime(null, null);
			String date = new SimpleDateFormat("dd/MM/yyyy , hh:mm:ss").format(System.currentTimeMillis( ));
			timeLabel.setFont(font);
			//time = LocalDateTime.now()
			timeLabel.setText(date);
			timeLabel.setBounds(600,175,200,40);
			mainFrame.getContentPane().add(timeLabel);
			
		}
		
		//// actions on Main Window

		private static void MainFrameAction() {
			
			//when "LOGIN" is clicked
			loginBtn.addActionListener(
					
						new java.awt.event.ActionListener()
						{
							public void actionPerformed(java.awt.event.ActionEvent event)
							{
								BuildLogInWindow();
							}

						}
					);
			
			//when "SEND" is clicked
			sendBtn.addActionListener(
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent event)
						{
							//Send_to_Server_from_Client();
							SendPressed();
						}

					});
			
			//when "DISCONNECT" is clicked
			disconnectBtn.addActionListener(
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent event)
						{
							try {
								DisconnectClient();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					});
			
			//when "Attach" is clicked 
			attachmentBtn.addActionListener(
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent event)
						{
							JFrame f = new JFrame("Image");
							//f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							// See http://stackoverflow.com/a/7143398/418556 for demo.
							f.setLocationByPlatform(true);

							f.pack();
							f.setSize(600, 800);
							f.setVisible(true);
							
							JFileChooser chooser = new JFileChooser();
							FileNameExtensionFilter filter = new FileNameExtensionFilter(
							    "JPG & GIF Images", "jpg", "gif");
							chooser.setFileFilter(filter);
							int returnVal = chooser.showOpenDialog(f);
							if(returnVal == JFileChooser.APPROVE_OPTION) {
							   System.out.println("You chose to open this file: " +
							        chooser.getSelectedFile().getName());
							}
							
							File file = chooser.getSelectedFile();
													 
		                    try {
		                       
								try {
									img = ImageIO.read(file);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                        ImageIcon icon=new ImageIcon(img); // ADDED
		                        pic.setIcon(icon); // ADDED

		                        Dimension imageSize = new Dimension(icon.getIconWidth(),icon.getIconHeight()); // ADDED
		                        pic.setPreferredSize(imageSize); // ADDED

		                        pic.revalidate(); // ADDED
		                        pic.repaint(); // ADDED                       
		                        pic.setBounds(0, 0, width, height);
		                        f.setVisible(false);
		                        
		                        pictureSend();
		                       
		                        
									//imageWindow.setVisible(false);

		                    }
		                    finally
		                    {}	
						}
						
				});
}
		//// start building log in window here //////////////////////////////////////////////////////////
		
		private static void BuildLogInWindow() {
			
			loginFrame.setLocation(250,100);
			loginFrame.setSize(400,250);
			loginFrame.setResizable(false);
			ConfigureLogInFrame();
			LogInFrameAction();
			loginFrame.setVisible(true);		
		}

		

		private static void ConfigureLogInFrame() {
			//Log In Frame
			Font font = new Font("Consolas",Font.PLAIN,14);
			Font font2 = new Font("Consolas",Font.BOLD,14);
			loginFrame.getContentPane().setBackground(new java.awt.Color(199,204,219));
			loginFrame.setFont(font);
			loginFrame.setTitle(userName + "'s Chat Box");
			//loginFrame.setBackground(new java.awt.Color(238,248,255));
			//loginFrame.setForeground(new java.awt.Color(42,50,75));
			loginFrame.setSize(400,250);
			loginFrame.setTitle("Log In As");
			loginFrame.setResizable(false);
			
			//Enter Button - will send text of name to main frame
			enterBtn.setBackground(new java.awt.Color(85,73,113));
			enterBtn.setForeground(new java.awt.Color(255,255,255));
			enterBtn.setText("ENTER");
			enterBtn.setFont(font2);
			enterBtn.setBounds(150,170,100,40);	
			loginFrame.getContentPane().add(enterBtn);		
			
			//Name Label - to inform the user to input a name
			nameLable.setForeground(new java.awt.Color(42,50,75));
			nameLable.setText("User Name   : ");
			nameLable.setFont(font);
			nameLable.setBounds(20,120,150,40);
			loginFrame.getContentPane().add(nameLable);	
			
			//IP label - to indicate user to type in IP address
			IPLabel.setBackground(new java.awt.Color(42,50,75));
			IPLabel.setText("IP address  :");
			IPLabel.setFont(font);
			IPLabel.setBounds(20, 20, 120, 40);
			//loginFrame.getContentPane().add(IPLabel);
			
			//port number label - to indicate user to enter port number
			portLabel.setForeground(new java.awt.Color(42,50,75));
			portLabel.setBounds(20, 70, 120, 40);
			portLabel.setText("Port Number :");
			portLabel.setFont(font);
			//loginFrame.getContentPane().add(portLabel);
			
			//Name Input - where you can type
			//nameInput.setBackground(new java.awt.Color(187,209,184));
			nameInput.setForeground(new java.awt.Color(42,50,75));
			nameInput.setBounds(125,120,150,40);
			//nameInput.setText("testing testing testing");
			loginFrame.getContentPane().add(nameInput);				
			
			//buffer - for WTH reason a object is missing
			buffer.setBounds(0,0,100,100);
			loginFrame.getContentPane().add(buffer);
			
			//port number input - where you can specify the port to use
			portNumInput.setForeground(new java.awt.Color(42,50,75));
			portNumInput.setBounds(125, 70, 150, 40);
			//loginFrame.getContentPane().add(portNumInput);
			
			//ip address input - where you type in the ip address
			IPAddInput.setForeground(new java.awt.Color(42,50,75));
			IPAddInput.setBounds(125, 20, 150, 40);
			//loginFrame.getContentPane().add(IPAddInput);
			
			//buffer - for WTH reason a object is missing
			buffer.setBounds(0,0,100,100);
			loginFrame.getContentPane().add(buffer);			
		}

		//// actions taken on Log In window
		private static void LogInFrameAction() {
			// when "ENTER" is clicked
			enterBtn.addActionListener(
					
					new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent event)
						{
							send_username_to_main();
						}
					}
				);
			
		}
		

		private static void send_username_to_main() {
			
			if(!nameInput.getText().equals(""))
			{
				userName = nameInput.getText().trim();
				mainFrame.setTitle(userName + "'s Chat Room");
				infoLabel.setText("Logged in as " + userName);
				
				//Client.hostName = IPAddInput.getText();
				//String portTemp = portNumInput.getText();
				//Client.portNumber = Integer.parseInt(portTemp);
				
				Server.UserList.add(userName);
				
				System.out.println("In the scope of send_username_to_main");
				
				Connect();

				loginBtn.setEnabled(false);
				disconnectBtn.setEnabled(true);
				sendBtn.setEnabled(true);
				attachmentBtn.setEnabled(true);
				loginFrame.setVisible(false);
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please enter your name!");
			}
				
		}	
		
		////send pressed
		private static void SendPressed() {
			// TODO Auto-generated method stub
			if(!inputTextArea.getText().equals(""))
	        {
	            Client.SEND(inputTextArea.getText());
	            inputTextArea.setText("");
	            inputTextArea.requestFocus();
	        }
			
		}
		
		//// Interacting with Client-Server function /////////////////////////////////////////////
		public static void pictureSend() {
			// TODO Auto-generated method stub
			
			for(int i=1;i<=Server.UserList.size();i++)
			{
				width = pic.getWidth();
			    height = pic.getHeight();
			    
				final JFrame temp = new JFrame("Picture to " + Server.UserList.get(i-1) + " from : " + userName);
			 	temp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 	
		        temp.setSize(width,height);		                        
		        temp.getContentPane().add(pic);
		        temp.setVisible(true);		       
			}		
			 
		}
		
		private static void Send_to_Server_from_Client() {
			// TODO Auto-generated method stub
			String sentMessage = new String();
			sentMessage = inputTextArea.getText();
			
			//if get Text 		
			if(sentMessage!=null)
			{
				Client.clientMessage = sentMessage;
				
				//create new socket / thread and make sure it's connected
				Connect();
				
				//sending
				try {
						System.out.println("sending message");
						outPW = new PrintWriter(clientSOCK.getOutputStream());
						outPW.println(userName + " : " + Client.clientMessage);
						outPW.flush();
										
						//try to echos out to all users
						Client temp = new Client(clientSOCK);
						Thread multiSocket = new Thread(temp);
						multiSocket.start();
						System.out.println("start runnable of client");
						
						
						//Client_GUI.conversationTextArea.append(userName + " : " + Client.clientMessage + "\n");
						inputTextArea.setText("");
					} 
				
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			// check connection first, if connection is established
			//to get text input from users and connect with client to let client transfer them to server
		}
		
		public static void Connect()
		{
			try {
					System.out.println("entering Connect()");
				
				//create a new socket which is used to pass to the clientSocket in Client.java
					final String hostName = "127.0.0.1";
					final int portNumber = 2048;
						try{
								System.out.println("Creating socket");
								clientSOCK = new Socket(hostName,portNumber);
								System.out.println("Created a new socket(Client.clientSocket)");
								//clientSOCK = new Socket(hostName,portNumber);
								//System.out.println("Created a new socket(Client_GUI.clientSOCK)");
								System.out.println("You've been connected to : " + hostName);
			
								//pass the newly created client to that in the Client.java
								SOCK = new Client(clientSOCK);
								System.out.println("socket returned");
								
								//send the name to show on the online list
								outPW = new PrintWriter(clientSOCK.getOutputStream());
								outPW.println(userName);
								System.out.println("User name is : " + userName);
								outPW.flush();				
								
								//create thread and use multi-threaded structure
								////must implement runnable interface on Client.java
								//Client temp = new Client();
								Thread multiSocket = new Thread(SOCK);
								//Thread multiSocket = new Thread(new Client);
								System.out.println("multi thread created");
								multiSocket.start();
								System.out.println("start runnable of server");
					
				} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				finally
						{}
			}
						finally
						{}		
		}
		
		//// Disconnecting a client from the chat room////////////////////////////////////////////

		private static void DisconnectClient() throws IOException {
			// TODO Auto-generated method stub
			//check connection and break it
			if(!clientSOCK.isConnected())
			{
				for(int i=1;i<=Server.ConnectionList.size();i++)
				{
					if(Server.ConnectionList.get(i)==clientSOCK)
					{
						Server.ConnectionList.remove(i);
						System.out.println(userName + " has logged out...");
					}
				}
			}
			
			conversationTextArea.append(userName + " has logged out...");
			
			try {
				Client.DISCONNECT();
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mainFrame.setVisible(false);
			//recognize the user disconnecting and delete it from the user list and end the main frame 
		}

		@Override//serve as server return runnable
		public void run() {
				// TODO Auto-generated method stub
				
				try{
				
					
					try {
							System.out.println("Entering inner runnable of Client_GUI");
							//get input and output and pass it out to server
							Scanner input = new Scanner(clientSOCK.getInputStream());
							PrintWriter output = new PrintWriter(clientSOCK.getOutputStream());
							//outPW.flush();

							while(true)
							{
								checkConnection();
							
								if(!input.hasNext())
								{
									return;
								}
								
								Client.clientMessage = input.nextLine();
								String inputLine = Client.clientMessage;
								System.out.println("Client " + userName + " said :" + inputLine);
								//if gets userName
								//if(input.hasNext())
								{
									
									for(int i=1;i<=Server.ConnectionList.size();i++)
									{
										Socket temp = Server.ConnectionList.get(i-1);
										outPW = new PrintWriter(temp.getOutputStream());									
										outPW.println(inputLine);
										outPW.flush();
										//Client_GUI.conversationTextArea.append(inputLine);
										
										if(Client_GUI.img!=null)
										{
											Client_GUI.pictureSend();
										}
										Client_GUI.img = null;
									}
									
								}	
								
							}					
					}
					
						//finally closes the socket	
						finally
						{
							Client.clientSocket.close();
						}
											
					} 
				catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Not getting input");
					}		
			}

		private void checkConnection() throws IOException {
			//checking connection
		if(!clientSOCK.isConnected())
			{
				////remove from connection list
				for(int i=1;i<=Server.ConnectionList.size();i++)
				{
					if(Server.ConnectionList.get(i)==clientSOCK)
					{
						Server.ConnectionList.remove(i);
					}
				}
				
				////send feed back to all user
				for(int i=1;i<=Server.ConnectionList.size();i++)
				{
					Socket temp =Server.ConnectionList.get(i-1);
					outPW = new PrintWriter(temp.getOutputStream());
					outPW.println(temp.getLocalAddress().getHostName() + "has logged out...");
					outPW.flush();
					
					
					//system feedback
					System.out.print(temp.getLocalAddress().getHostName() + "disconnected!");
				}
			}
			
		}
			
		
}


