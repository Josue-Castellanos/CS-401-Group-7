package clientPkg;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.sound.midi.Receiver;

import packetPkg.*;
import serverPkg.*;

public class Client {

	// driver code
		public static void main(String[] args)
		{
			// establish a connection by providing host and port
			// number
			try (Socket socket = new Socket("localhost", 1234)) {
				
				System.out.println("Connected to " + "localhost" + ":" + 1234);
				try {
					// Output stream socket.
			        OutputStream outputStream = null;
		
			        // Create object output stream from the output stream to send an object through it
			        ObjectOutputStream objectOutputStream;
			        
			        // get the input stream from the connected socket
			        InputStream inputStream = null;
		
			        // create a ObjectInputStream so we can read data from it.
			        ObjectInputStream objectInputStream;
			        
			        // object of scanner class
					Scanner sc = new Scanner(System.in);
					String line = null;
					
					//Login process start
					User currentUser = new User();	//Build new object of type User
					Packet login = new Packet(PacketType.LOGIN, RequestType.NULL, currentUser);
					
			/*********************	Login Loop	********************************/
					while(login.getStatusType() != StatusType.SUCCESS ) {
						//GUI - Replace with something that can connect to the GUI
				        System.out.println("\nPlease enter your username: ");			     
						line = sc.nextLine();	// reading text from user						
						
						currentUser.setUsername(line);	//set username in object of type User
						
						//GUI - Replace with something that can connect to the GUI
				        System.out.println("\nPlease enter your password: ");			     
						line = sc.nextLine();	// reading text from user		
						
						currentUser.setPassword(line);						
						login.setUser(currentUser);			        
				        
				        outputStream = socket.getOutputStream();
				        objectOutputStream = new ObjectOutputStream(outputStream);
				        objectOutputStream.writeObject(login);
				        
				        inputStream = socket.getInputStream();
				        objectInputStream = new ObjectInputStream(inputStream);
				        login = (Packet) objectInputStream.readObject();
				        
				        //GUI - Replace with message in GUI
				        System.out.println("\nAttempting login...");
				        
				        if(login.getStatusType() == StatusType.SUCCESS) {
				        	System.out.println("Login successful");	//GUI - Message indicating successful login	
				        }
				        else {
				        	//GUI - Message indicating failure, and restart.
				        }
					}//Exit the Login Loop
			                	
		/***************	Enter the Functional Branch	*************************/
				//1. Load User Data from Server
					//Create the local client objects used for the session. Will get periodically updated by the server.
					currentUser = new User(login.getUser());
					Packet packetData = new Packet();
					ArrayList<serverPkg.Receiver.Group> clientGroups = new ArrayList<serverPkg.Receiver.Group>();
					ArrayList<serverPkg.Receiver.Chat> clientChats = new ArrayList<serverPkg.Receiver.Chat>();
					ArrayList<serverPkg.Receiver.Group> inviteGroups = new ArrayList<serverPkg.Receiver.Group>();
					
					
					//For Loop Request for Data from Server. Start by getting size of lists.
					ArrayList<String> inviteList = new ArrayList<String>(currentUser.getInviteList());
					int sizeInviteList = inviteList.size();
					
					ArrayList<String> groupList = new ArrayList<String>(currentUser.getGroupList());
					int sizeGroupList = groupList.size();
					
					ArrayList<String> chatList = new ArrayList<String>(currentUser.getChatList());
					int sizeChatList = chatList.size();
					
					//Loop to get InviteList
					for(int i = 0; i < sizeInviteList; i++) {
						packetData = new Packet(PacketType.REQUEST, RequestType.INVITE_LIST, currentUser, inviteList.get(i));
						outputStream = socket.getOutputStream();
				        objectOutputStream = new ObjectOutputStream(outputStream);
				        objectOutputStream.writeObject(packetData);	//Send packet to server, with inviteList[i] as the string argument.
				        
				        inputStream = socket.getInputStream();
				        objectInputStream = new ObjectInputStream(inputStream);
				        packetData = (Packet) objectInputStream.readObject();
				        inviteGroups.add(packetData.getGroup());
					}	
					
					//Loop to get Groups List
					for(int i = 0; i < sizeGroupList; i++) {
						packetData = new Packet(PacketType.REQUEST, RequestType.INVITE_LIST, currentUser, groupList.get(i));
						outputStream = socket.getOutputStream();
				        objectOutputStream = new ObjectOutputStream(outputStream);
				        objectOutputStream.writeObject(packetData);	//Send packet to server, with inviteList[i] as the string argument.
				        
				        inputStream = socket.getInputStream();
				        objectInputStream = new ObjectInputStream(inputStream);
				        packetData = (Packet) objectInputStream.readObject();
				        clientGroups.add(packetData.getGroup());
					}	
					
					//Loop to get Chats List
					for(int i = 0; i < sizeChatList; i++) {
						packetData = new Packet(PacketType.REQUEST, RequestType.INVITE_LIST, currentUser, chatList.get(i));
						outputStream = socket.getOutputStream();
				        objectOutputStream = new ObjectOutputStream(outputStream);
				        objectOutputStream.writeObject(packetData);	//Send packet to server, with inviteList[i] as the string argument.
				        
				        inputStream = socket.getInputStream();
				        objectInputStream = new ObjectInputStream(inputStream);
				        packetData = (Packet) objectInputStream.readObject();
				        clientChats.add(packetData.getChat());
					}	
					
					//We should now have all our data from the Server to begin.				
					
					//2. Navigate from Home Screen
					
					//3. 
					//Packet message = new Packet(PacketType.text, StatusType.undefined, "");
					while (true) {
						System.out.println("\n\nWrite a text message. To exit, write 'logout'");
						
						// reading text from user
						line = sc.nextLine();

						// sending the user input to server
						line = line.toLowerCase();
						if(line.equals("logout")) {
							break;
						}
						/*Packet.setText(line);
						objectOutputStream.writeObject(message);
						message = (Packet) objectInputStream.readObject();
						System.out.println("\nMessage Received from Server: " + message.getText() + "\n");*/
					}
					
					Packet logout = new Packet(PacketType.LOGOUT, RequestType.NULL, currentUser);
					objectOutputStream = new ObjectOutputStream(outputStream);
					objectOutputStream.writeObject(logout);
					
					objectInputStream = new ObjectInputStream(inputStream);
					logout = (Packet) objectInputStream.readObject();
					if(logout.getStatusType() == StatusType.SUCCESS) {
						System.out.println("\n\nLogged out successfully.");
					}
					else {
						System.out.println("\n\nThere was an error. You will be logged out");
					}
					
					// closing the scanner object
					sc.close();
					
					//close the socket.
					socket.close();
					
					//Exit out of application
					System.exit(0);
	        }			
			catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}