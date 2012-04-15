package campfire4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

public class CampfireAppender extends AppenderSkeleton {

	private Application application;
	private Room room;
	private String token;
	private Campfire campfire;

	public void setApplication(String appName) {
		this.application = Application.aNew(appName);
	}

	public String getApplication() {
		return this.application.name;
	}

	public void setRoomId(int roomId) {
		this.room = Room.aNew(roomId);
	}

	public int getRoomId() {
		return this.room.id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

	@Override
	public void close() {
		disconnectCampfireService();
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent logEvent) {

		if (this.layout == null) {
			errorHandler.error("No layout for appender " + name, null,
					ErrorCode.MISSING_LAYOUT);
			return;
		}

		System.out.println("Application Name : " + application.name);
		System.out.println("Room Id : " + room.id);
		System.out.println("Token : " + token);
		System.out.println("Message : " + this.layout.format(logEvent));
		//connectCampfireService().write(
		//		Message.wrap(logEvent).layout(this.layout));
	}

	private Campfire connectCampfireService() {

		if (campfire != null) {
			return campfire;
		}

		campfire = Campfire.app(application).login(token).to(room);

		return campfire;
	}

	private void disconnectCampfireService() {
		if (campfire != null) {
			campfire.close();
		}
	}
}
