import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Message {

	private int id;
	private String content;
	private LocalDateTime timestamp;
	private boolean read;
	private boolean deleted;
	private Chat chat;
	private ArrayList<Reaction> reactions;
	private HashMap<Integer,Notification> notifications;
	private User author;

}
