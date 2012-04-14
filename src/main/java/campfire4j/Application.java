package campfire4j;

public class Application {
	
	public final String name;
	
	private Application (String name) {
		this.name = name;
	}
	
	public static final Application aNew (String name) {
		return new Application(name);
	}
}
