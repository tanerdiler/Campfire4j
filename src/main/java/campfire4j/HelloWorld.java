package campfire4j;

import java.io.IOException;

public class HelloWorld {
	public static void main(String[] args) throws IOException {
//		URL url = new URL("https://linkznet.campfirenow.com/room/487059/speak.xml");
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Authorization", "Basic " + Base64.encodeBase64String("AUTH_KEY:X".getBytes()));
//        con.setRequestProperty("Content-Type", "application/xml");
//        con.setDoOutput(true);
//        con.setDoInput(true);
//        
//
//        String mesaj = "<message><body>Hello</body></message>";
//        byte[] mesajBytes = mesaj.getBytes("utf-8");
//        con.setRequestProperty("Content-Length", String.valueOf(mesajBytes.length));
//        OutputStream os = con.getOutputStream();
//        os.write(mesajBytes);
//        os.flush();
//        int responseCode = con.getResponseCode();
//        System.out.println("response code " + responseCode);
        
		Campfire.app(Application.aNew("linkznet"))
		.login("1848335ce7a2fd3c20c86bfdc994df8e57ac2182")
		.to(Room.aNew(487059))
		.write(Message.aNew("bu da deneme"));
	}
	
}
