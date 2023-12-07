package packetPkg;

import serverPkg.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Packet implements Serializable{
	private static final long serialVersionUID = 1L;	//Warning was given by IDE. This line was added to satisfy the warning condition.
	protected PacketType type;
	protected RequestType request;
	protected StatusType status;
	protected Message message;
	protected ArrayList<Message> msgList;
	protected Receiver location;
	protected Receiver.Group group;
	protected Receiver.Chat chat;
	protected ArrayList<Receiver> receiverList;
	protected ArrayList<Receiver.Group>groupList;
	protected ArrayList<Receiver.Chat>chatList;
	protected User user;
	protected User.GeneralUser genUser;
	protected User.ITUser ITUser;
	protected ArrayList<User> userList;
	protected String string;
	
	//Default Constructor
	public Packet(){
		this(null, null, null, null, null,
				null, null, null, null,
				null, null);
	}	
	
	//Login and Logout Constructor
	public Packet(PacketType packet, RequestType request, User newUser){
		this.setPacketType(packet);
		this.setRequestType(request);
		this.setUser(newUser);
	}
	
	//SEND_MESSAGE_GROUP Constructor
	public Packet(PacketType packet, RequestType request, User fromUser, Receiver.Group trgtGroup, Message newMessage) {
		this.setPacketType(packet);
		this.setRequestType(request);
		this.setUser(fromUser);
		this.setGroup(trgtGroup);
		this.setMessage(newMessage);
	}
	
	//Request BLOCK_LIST, INVITE_LIST, GROUP_LIST, CHAT_LIST Constructor
		public Packet(PacketType packet, RequestType request, User newUser, String list){
			this.setPacketType(packet);
			this.setRequestType(request);
			this.setUser(newUser);
			this.setString(list);
		}
	
	//RECEIVE_MESSAGE_GROUP and CREATE_GROUP Constructor 
	public Packet(PacketType packet, RequestType request, Receiver.Group trgtGroup) {
		this.setPacketType(packet);
		this.setRequestType(request);
		this.setGroup(trgtGroup);
	}
	
	//SEND_MESSAGE_CHAT Constructor
	public Packet(PacketType packet, RequestType request, User fromUser, Receiver.Chat trgtChat, Message newMessage) {
		this.setPacketType(packet);
		this.setRequestType(request);
		this.setUser(fromUser);
		this.setChat(trgtChat);
		this.setMessage(newMessage);
	}
	
	//RECEIVE_MESSAGE_CHAT and CREATE_CHAT Constructor
	public Packet(PacketType packet, RequestType request, Receiver.Chat trgtChat) {
		this.setPacketType(packet);
		this.setRequestType(request);
		this.setChat(trgtChat);
	}

	//JOIN_GROUP and LEAVE_GROUP Constructor
	public Packet(PacketType packet, RequestType request,Receiver.Group trgtGroup, User trgtUser) {
		this.setPacketType(packet);
		this.setRequestType(request);
		this.setGroup(trgtGroup);
		this.setUser(trgtUser);
	}
	
	//RECEIVE_INVITE_LIST Constructor
	public Packet(PacketType packet, RequestType request, User trgtUser, ArrayList<Receiver> inviteList) {
		this.setPacketType(packet);
		this.setRequestType(request);
		this.setUser(trgtUser);
		this.setReceiverList(inviteList);
	}
	
	//KICK_USER, REPORT_USER, BLOCK_USER   Constructor
	public Packet(PacketType packet, RequestType request, User trgtUser, Receiver.Group trgtGroup) {
		this.setPacketType(packet);
		this.setRequestType(request);
		this.setUser(trgtUser);
		this.setGroup(trgtGroup);
	}
	
	//Full custom Constructor
	public Packet(PacketType newType, RequestType newRequest, StatusType newStatus, Message newMessage,
			ArrayList<Message> newMsgList, Receiver.Group newGroup, Receiver.Chat newChat, ArrayList<Receiver> newReceiverList,
			User newUser, ArrayList<User> newUserList, String newString) {
		this.setPacketType(type);
		this.setRequestType(request);
		this.setStatusType(status);
		this.setMessage(message);
		this.setMsgList(newMsgList);
		this.setGroup(newGroup);;
		this.setChat(newChat);
		this.setReceiverList(newReceiverList);
		this.setUser(newUser);
		this.setUserList(newUserList);
		this.setString(newString);		
	}
	/*****************Begin	Getters ***************************************/

	public PacketType getPacketType() {
		return type;
	}
	
	public RequestType getRequestType() {
		return request;
	}
	

	public StatusType getStatusType() {
		return status;
	}

	public Message getMessage() {
		return message;
	}
	

	public ArrayList<Message> getMsgList(){
		return msgList;
	}
	
	public Receiver getLocation(){
		return location;
	}
	
	public Receiver.Group getGroup(){
		return group;
	}
	
	public Receiver.Chat getChat(){
		return chat;
	}
	
	public ArrayList<Receiver> getReceiverList(){
		return receiverList;
	}
	
	public ArrayList<Receiver.Group> getGroupList(){
		return groupList;
	}
	
	public ArrayList<Receiver.Chat> getChatList(){
		return chatList;
	}
	
	public User getUser(){
		return user;
	}
	public User.GeneralUser getGenUser(){
		return genUser;
	}
	
	public User getITUser(){
		return ITUser;
	}
	
	public ArrayList<User> getUserList(){
		return userList;
	}
	
	public String getString(){
		return string;
	}
	/*****************End	Getters ***************************************/
	/*****************Begin	Setters ***************************************/
	public void setPacketType(PacketType type) {
		this.type = type;
	}
	
	public void setRequestType(RequestType request) {
		this.request = request;
	}
	
	public void setStatusType(StatusType status) {
		this.status = status;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public void setMsgList(ArrayList<Message> newList){
		this.msgList = newList;
	}
	
	public void setReceiver(Receiver newLocation){
		this.location = newLocation;
	}
	
	public void setGroup(Receiver.Group newGroup){
		this.group = newGroup;
	}
	
	public void setChat(Receiver.Chat newChat){
		this.chat = newChat;
	}
	
	public void setReceiverList(ArrayList<Receiver> newList){
		this.receiverList =newList;
	}
	
	public void setGroupList(ArrayList<Receiver.Group> newList){
		this.groupList = newList;
	}
	
	public void setChatList(ArrayList<Receiver.Chat> newList){
		this.chatList = newList;
	}
	
	public void setUser(User newUser){
		this.user = newUser;
	}
	
	public void setGenUser(User.GeneralUser newUser){
		this.genUser = newUser;
	}
	
	public void setITUser(User.ITUser newUser){
		this.ITUser = newUser;
	}
	
	public void setUserList(ArrayList<User> newUser){
		this.userList = newUser;
	}
	
	public void setString(String newString){
		this.string = newString;
	}
	/*****************End Setters ***************************************/
}
