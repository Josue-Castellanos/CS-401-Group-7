package testing;

import org.junit.Test;
import org.junit.Assert;
import java.util.Date;

public class MessageTest {

    @Test
    public void testGetFrom() {
        Message message = new Message("12345", "67890", "Hello World!", MsgType.TEXT);
        Assert.assertEquals("12345", message.getFrom());
    }

    @Test
    public void testGetTo() {
        Message message = new Message("12345", "67890", "Hello World!", MsgType.TEXT);
        Assert.assertEquals("67890", message.getTo());
    }

    @Test
    public void testGetMsgType() {
        Message message = new Message("12345", "67890", "Hello World!", MsgType.TEXT);
        Assert.assertEquals(MsgType.TEXT, message.getMsgType());
    }

    @Test
    public void testGetMessage() {
        Message message = new Message("12345", "67890", "Hello World!", MsgType.TEXT);
        Assert.assertEquals("Hello World!", message.getMessage());
    }

    @Test
    public void testGetDate() {
        Date now = new Date();
        Message message = new Message("12345", "67890", "Hello World!", MsgType.TEXT);
        long timeDiff = message.getDate().getTime() - now.getTime();

        // Assuming the time difference between the creation of the 'now' object and the message object is less than 1000ms
        Assert.assertTrue(timeDiff < 1000);
    }
}