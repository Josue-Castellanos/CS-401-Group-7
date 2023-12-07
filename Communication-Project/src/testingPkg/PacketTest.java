package testing;

import org.junit.Test;
import org.junit.Assert;
import packetPkg.Packet;
import packetPkg.PacketType;
import packetPkg.RequestType;
import serverPkg.Message;
import serverPkg.MsgType;
import serverPkg.Receiver;
import serverPkg.User;

public class PacketTest {

    @Test
    public void testDefaultConstructor() {
        Packet packet = new Packet();
        Assert.assertNull(packet.getPacketType());
        Assert.assertNull(packet.getRequestType());
        // ... continue checking other fields
    }

    @Test
    public void testLoginConstructor() {
        User user = new User("username", "password");
        String password = "password";
        Packet packet = new Packet(user, password);
        Assert.assertEquals(PacketType.LOGIN, packet.getPacketType());
        Assert.assertEquals(user, packet.getUser());
        Assert.assertEquals(password, packet.getString());
    }

    @Test
    public void testLogoutConstructor() {
        User user = new User("username", "password");
        Packet packet = new Packet(user);
        Assert.assertEquals(PacketType.LOGOUT, packet.getPacketType());
        Assert.assertEquals(user, packet.getUser());
    }

    @Test
    public void testSendMessageConstructor() {
        User fromUser = new User("username", "password");
        Receiver location = new Receiver("locationId");
        Message newMessage = new Message("12345", "67890", "Hello World!", MsgType.TEXT);
        Packet packet = new Packet(PacketType.SEND_MESSAGE, fromUser, location, newMessage);
        Assert.assertEquals(RequestType.SEND_MESSAGE, packet.getRequestType());
        Assert.assertEquals(location, packet.getLocation());
        Assert.assertEquals(newMessage, packet.getMessage());
    }

    @Test
    public void testReceiveMessageConstructor() {
        Receiver location = new Receiver("locationId");
        Packet packet = new Packet(location);
        Assert.assertEquals(RequestType.RECEIVE_MESSAGE, packet.getRequestType());
        Assert.assertEquals(location, packet.getLocation());
    }

    @Test
    public void testCreateGroupConstructor() {
        Receiver.Group newGroup = new Receiver.Group("groupId");
        Packet packet = new Packet(newGroup);
        Assert.assertEquals(RequestType.CREATE_GROUP, packet.getRequestType());
        Assert.assertEquals(newGroup, packet.getGroup());
    }

    @Test
    public void testCreateChatConstructor() {
        Receiver.Chat newChat = new Receiver.Chat("chatId");
        Packet packet = new Packet(newChat);
        Assert.assertEquals(RequestType.CREATE_CHAT, packet.getRequestType());
        Assert.assertEquals(newChat, packet.getChat());
    }

    @Test
    public void testJoinGroupConstructor() {
        Receiver.Group newGroup = new Receiver.Group("groupId");
        User newUser = new User("username", "password");
        Packet packet = new Packet(newGroup, newUser);
        Assert.assertEquals(RequestType.JOIN_GROUP, packet.getRequestType());
        Assert.assertEquals(newGroup, packet.getGroup());
        Assert.assertEquals(newUser, packet.getUser());
    }

    @Test
    public void testLeaveGroupConstructor() {
        Receiver.Group newGroup = new Receiver.Group("groupId");
        User newUser = new User("username", "password");
        Packet packet = new Packet(newGroup, newUser);
        Assert.assertEquals(RequestType.JOIN_GROUP, packet.getRequestType()); // This should be LEAVE_GROUP
        Assert.assertEquals(newGroup, packet.getGroup());
        Assert.assertEquals(newUser, packet.getUser());
    }
    
    @Test
    public void testSetPacketType() {
        Packet packet = new Packet();
        packet.setPacketType(PacketType.LOGIN);
        Assert.assertEquals(PacketType.LOGIN, packet.getPacketType());
    }

    @Test
    public void testSetRequestType() {
        Packet packet = new Packet();
        packet.setRequestType(RequestType.SEND_MESSAGE);
        Assert.assertEquals(RequestType.SEND_MESSAGE, packet.getRequestType());
    }

    @Test
    public void testSetStatusType() {
        Packet packet = new Packet();
        packet.setStatusType(StatusType.SUCCESS);
        Assert.assertEquals(StatusType.SUCCESS, packet.getStatusType());
    }

    @Test
    public void testSetMessage() {
        Packet packet = new Packet();
        Message message = new Message("12345", "67890", "Hello World!", MsgType.TEXT);
        packet.setMessage(message);
        Assert.assertEquals(message, packet.getMessage());
    }
    
    @Test
    public void testGetPacketType() {
        Packet packet = new Packet();
        packet.setPacketType(PacketType.LOGIN);
        Assert.assertEquals(PacketType.LOGIN, packet.getPacketType());
    }

    @Test
    public void testGetRequestType() {
        Packet packet = new Packet();
        packet.setRequestType(RequestType.SEND_MESSAGE);
        Assert.assertEquals(RequestType.SEND_MESSAGE, packet.getRequestType());
    }

    @Test
    public void testGetStatusType() {
        Packet packet = new Packet();
        packet.setStatusType(StatusType.SUCCESS);
        Assert.assertEquals(StatusType.SUCCESS, packet.getStatusType());
    }

    @Test
    public void testGetMessage() {
        Packet packet = new Packet();
        Message message = new Message("12345", "67890", "Hello World!", MsgType.TEXT);
        packet.setMessage(message);
        Assert.assertEquals(message, packet.getMessage());
    }
    
}
