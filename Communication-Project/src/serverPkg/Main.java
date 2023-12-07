package serverPkg;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComSystem comSystem = new ComSystem();
		Server ser = new Server();
		ser.start(comSystem);
	}

}
