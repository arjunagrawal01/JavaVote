
import java.net.*;
import java.io.*;

/**
 * 
 * @author Arjun Agrawal
 * Helps vote client vote to the vote server
 */
public class VoteClientHelper {
	private MyStreamSocket mySocket;
	private String serverHost;
	private int serverPort;
	static final String endMessage = ".";

	public VoteClientHelper(String hostName, String portNum) throws SocketException, UnknownHostException, IOException {
		this.serverHost = (hostName);
		this.serverPort = Integer.parseInt(portNum);
		// instantiates a datagram socket for both sending
		// and receiving data
		this.mySocket = new MyStreamSocket(this.serverHost, this.serverPort);
		System.out.println("connection request made");
		mySocket.sendMessage("I want to vote");
	}
	
	/**
	 * Send a message to the server
	 * @param message
	 * @throws SocketException
	 * @throws IOException
	 */
	public void send(String message) throws SocketException, IOException {
		mySocket.sendMessage(message);
	}
	
	/**
	 * Receives a message from the server
	 * @return message
	 * @throws SocketException
	 * @throws IOException
	 */
	public String recieve() throws SocketException, IOException {
		return mySocket.receiveMessage();
	}
	
	/**
	 * Finish Socket Connection
	 * @throws SocketException
	 * @throws IOException
	 */
	public void done() throws SocketException, IOException {
		mySocket.sendMessage(endMessage);
		mySocket.close();
	} // end done

} // end class
