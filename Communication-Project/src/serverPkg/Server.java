package serverPkg;
import java.io.*;
import java.net.*;

import packetPkg.*;


public class Server {
	public Server() {}
	
	public void start(ComSystem comSystem) {
		
		ServerSocket server = null;
		
		try {
			
			// server is listening on port 1234
			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			// running infinite loop for getting
			// client request
			while (true) {
				
				// socket object to receive incoming client
				// requests
				Socket client = server.accept();

				// Displaying that new client is connected
				// to server
				System.out.println("New client connected"
								+ client.getInetAddress()
										.getHostAddress());

				// create a new thread object
				ClientHandler clientSock = new ClientHandler(client, comSystem);

				// This thread will handle the client
				// separately
				new Thread(clientSock).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
		// ClientHandler class
		private static class ClientHandler implements Runnable {
			private final Socket clientSocket;
			private ComSystem comSystem;
		    private ObjectOutputStream out;
		    private ObjectInputStream in;
			// Constructor
			public ClientHandler(Socket socket, ComSystem comSystem) throws IOException
			{
				this.clientSocket = socket;
				this.comSystem = comSystem;
		        out = new ObjectOutputStream(clientSocket.getOutputStream());
		        in = new ObjectInputStream(clientSocket.getInputStream());
			}

			public void run(){
		        try {
		            Packet packet = (Packet) in.readObject();
		            switch (packet.getPacketType()) {
		            case LOGIN:
		            	packet = comSystem.login(packet.getUser());
		            	out.writeObject(packet);
	            		out.flush();
	            		while(true) {
			            	switch (packet.getPacketType())  {
				            case LOGOUT:
				            	comSystem.logout(packet.getUser());
				            	out.writeObject(packet);
			            		out.flush();
				            	clientSocket.close();
				            	break;
				            case REQUEST:
				            	switch (packet.getRequestType()) {
				            	case SEND_MESSAGE_GROUP:
				            		comSystem.writeToGroup(packet.getGroup(), packet.getMessage());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case RECEIVE_MESSAGE_GROUP:
				            		packet = comSystem.readGroup(packet.getGroup());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case SEND_MESSAGE_CHAT:
				            		comSystem.writeToChat(packet.getChat(), packet.getMessage());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case RECEIVE_MESSAGE_CHAT:
				            		packet = comSystem.readChat(packet.getChat());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case CREATE_GROUP:
				            		comSystem.addGroup(packet.getGroup());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case CREATE_CHAT:
				            		comSystem.addChat(packet.getChat());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case JOIN_GROUP:
				            		comSystem.addUserToGroup(packet.getUser());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case LEAVE_GROUP:
				            		comSystem.removeUserFromGroup(packet.getString());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case KICK_USER:
				            		comSystem.removeUserFromGroup(packet.getString());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case SEND_INVITE:
				            		packet = comSystem.sendInvite(packet.getUser(), packet.getGroup());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case RECEIVE_INVITE:
				            		//comSystem.addUserToGroup(packet.getUser(), packet.getGroup());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	case REPORT_USER:
				            		//ITuser is made?
					            	break;
				            	case BLOCK_USER:
				            		//packet = comSystem.addBlockList(packet.getUser(), packet.getBlocked());
				            		packet.setStatusType(StatusType.SUCCESS);
				            		out.writeObject(packet);
				            		out.flush();
					            	break;
				            	default:
				            		break;
				            	}
				            default:
			            		break;
			            	}		            			            	
		                }
		            default:
	            		break;
		            }
		        }
				catch (IOException | ClassNotFoundException e) {
		        	e.printStackTrace();
		        }
		
	          }
			}
}

