package testingPkg;
import static org.junit.Assert.*;
import org.junit.Test;
import serverPkg;


public class SystemTest {

	@Test
	public void addConnectedUserTest() {
		User user = new User("display", "username", "password", "GENERAL");
		addConnectedUser(user);
		assertEquals(connectedUser[0].getUsername(), "username");
	}
	
	@Test
	public void verifyTest() {
		User user = new User("display", "username", "password", "GENERAL");
		addConnectedUser(user);
		assertTrue(verify(user));
	}

}
