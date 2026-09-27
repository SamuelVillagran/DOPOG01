import java.util.HashMap;

public class User {

	private int id;
	private String name;
	private String email;
	private String type;
	private String status;
	private HashMap<Integer,Room> rooms;
	private HashMap<Integer,Notification> inbox;

}
