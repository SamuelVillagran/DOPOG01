import java.time.LocalDate;
import java.util.HashMap;

public class Room {

	private int Id;
	private String name;
	private String description;
	private LocalDate createdDate;
	private int capacity;
	private boolean private_;
	private HashMap<Integer,User> participants;
	private HashMap<Integer,Chat> chats;

}
