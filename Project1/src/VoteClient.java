
import java.io.*;

/**
 * 
 * @author Arjun Agrawal
 * Client to vote to the server
 */
public class VoteClient {
	static final String endMessage = ".";

	public static void main(String[] args) {

		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);

		try {
			System.out.println("Welcome to the Vote client.\n" + "What is the name of the server host?");
			String hostName = br.readLine();
			if (hostName.length() == 0) // if user did not enter a name
				hostName = "localhost"; // use the default host name
			System.out.println("What is the port number of the server host?");
			String portNum = br.readLine();
			if (portNum.length() == 0)
				portNum = "7"; // default port number

			VoteClientHelper helper = new VoteClientHelper(hostName, portNum);
			boolean done = false;
			String message;
			
			System.out.println(helper.recieve());
			
			//Initial Vote
			message = br.readLine();
			helper.send(message);
			while (!done) {
				
				//Vote Query, until . to exit
				System.out.println(helper.recieve());
				message = br.readLine();
				helper.send(message);
				if ((message.trim()).equals(endMessage)) {
					done = true;
					helper.done();
				} else {
					System.out.println(helper.recieve());
				}
			} // end while
		} // end try
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// end class
}
