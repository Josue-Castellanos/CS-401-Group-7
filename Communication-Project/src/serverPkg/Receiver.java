package serverPkg;

import java.util.Date;
import java.util.ArrayList;
import java.util.*;

//Implemented by Nabil Alhamal
public class Receiver {
	//Fields for the Receiver Class
	protected ArrayList<String> userList;	//Holds the list of users
	protected ArrayList<Message> msgList;	//Holds the list of message objects
	protected Date created;		//Date the Receiver was created
	protected String createdByUser; //Name of user who created the Receiver
	
	public ArrayList<Message> getMsgList() {
		return msgList;
	}
	
	public Message getMessage(int index) {
		return msgList.get(index);
	}
	
	public ArrayList<String> getUserList() {
		return userList;
	}
	
	public String getUserID(int index) {
		return userList.get(index);
	}
	
	public String getCreator() {
		return createdByUser;
	}
	
	public String getDate() {
		return created.toString();
	}
	
	public void addMessage(Message newMessage) {
		//Needs implementation to add to the end of the list. May want to change this to a sorted list
		msgList.add(newMessage);
	}
	
	//Adds a user to the list
	public void addUser(String userID) {
		int index = findHelper(userID, userList);
		if (index == -1) {
			userList.add(userID);
			Collections.sort(userList);
		}
		else {
			//System.out.println("\nUser " + userID + " is already a member. \n");
		}
	}
	
	//Removes a user from the list
	public void removeUser(String userID) {
		int index = findHelper(userID, userList);
		if (index != -1) {
			userList.remove(userID);
		}
		else {
			//System.out.println("\nUser " + userID + " is already a member. \n");
		}
	}
	
	//Removes a moderator from the list
	//##############	Child Class - Chat	#######################################
	public class Chat extends Receiver {
		//Fields for the Chat Class - Child of Receiver
		private String chatID;
		private String chatName;
		public static int chatCount = 0; //used to generate chatID uniqueness.
		
		//Constructor
		public Chat(String user, ArrayList<String> userList, ArrayList<Message> msgList) {
			this.createdByUser = user;
			this.userList = userList;
			this.msgList = msgList;
			this.created = new Date();
			chatID = idHelper(chatCount, "Cht-");
			chatCount++;			
		}
		
		public String getChatID() {
			return chatID;
		}
		
		public String getChatName() {
			return chatName;
		}
		
		public void setChatName(String newChatName) {
			chatName = newChatName;
		}

	}

	//##############	Child Class - Group	#######################################
	public class Group extends Receiver{
		//Fields for the Group Class - Child of Receiver
		private String leadMod;			//Current lead moderator of a group - Default is the creator of the group
		private ArrayList<String> moderators;	//Additional moderators added by the lead moderator
		private String groupID;			//Uniquely Generated groupID
		private String groupName;		//Name for the Group. Doesn't have to be unique at the moment. Can implement uniqueness later if needed.
		public boolean isPrivate;		//Indicates whether the group is designated private or public. If private, should not be accessible by users not on the userList.
		public static int groupCount = 0; 	//used to generate groupID uniqueness.
		
		//Group Constructor
		public Group(String user, boolean privacy, String newGroupName) {
			leadMod = user;
			createdByUser = user;
			//addUser to the list. Need to implement.
			isPrivate = privacy;
			groupName = newGroupName;
			created = new Date();
			groupID = idHelper(groupCount, "Grp-");
			groupCount++;
		}

		public String getGroupID() {
			return groupID;
		}
		
		public String getLeadModerator() {
			return leadMod;
		}
		
		public void changeLeadModerator(String userID) {
			leadMod = userID;
		}
		
		public ArrayList<String> getModerators() {
			return moderators;
		}
		
		public String getGroupname() {
			return groupName;
		}	
		
		//Adds a moderator to the list
		public void addModerator(String userID) {
			int index = findHelper(userID, moderators);
			if (index == -1) {
				moderators.add(userID);
				Collections.sort(moderators);
			}
			else {
				//System.out.println("\nUser " + userID + " is already a member. \n");
			}
		}
		
		//Removes a moderator from the list
		public void removeModerator(String userID) {
			int index = findHelper(userID, moderators);
			if (index != -1) {
				moderators.add(userID);
			}
			else {
				//System.out.println("\nUser " + userID + " is already a member. \n");
			}
		}
		
		public void sendInvite(String userID) {
			//Need to implement
		}
		
		public void deleterGroup() {
			//Need to implement
		}
		
	}
	
	//Generates IDs for groups and chats.
	private String idHelper(int counter, String receiver ) {
		String temp = new String();
		temp = ("00000000" + Integer.toString(counter)).substring(Integer.toString(counter).length()); //Converts counter to a string, and adds leading zeroes.
		return receiver + temp;
	}
	
	//Searches for in the list
	private int findHelper(String userID, ArrayList<String> userList) {
		for(int i = 0; i < userList.size(); i++) {
			if (userID == userList.get(i)){
				//If found, return index
				return i;
			}
		}
		//If not found, return -1;
		return -1;
	}

}
