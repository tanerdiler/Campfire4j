package campfire4j;

public class Room {

	public final int id;

	public Room(int id) {
		this.id = id;
	}

	public static final Room aNew (int id) {
		return new Room(id);
	}
}
