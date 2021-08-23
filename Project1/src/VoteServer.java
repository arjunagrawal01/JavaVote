import java.io.*;
import java.net.*;


/**
 * 
 * @author Arjun Agrawal
 * Server to host a vote, keeps count of all the votes. 
 */
public class VoteServer {

	/**
	 * This module contains the application logic of an echo server which uses a
	 * stream-mode socket for interprocess communication. Unlike EchoServer2, this
	 * server services clients concurrently. A command-line argument is required to
	 * specify the server port.
	 * 
	 * @author M. L. Liu
	 */

	public static void main(String[] args) {
		int serverPort = 7; // default port
		String message;
		Counts count = new Counts(0, 0, 0);

		if (args.length == 1)
			serverPort = Integer.parseInt(args[0]);
		try {
			// instantiates a stream socket for accepting connections
			ServerSocket myConnectionSocket = new ServerSocket(serverPort);
			System.out.println("Vote Server ready.");
			while (true) {
				// forever loop
				// wait to accept a connection;
				/**/ System.out.println("Waiting for a connection.");
				MyStreamSocket myDataSocket = new MyStreamSocket(myConnectionSocket.accept());
				/**/ System.out.println("connection accepted");
				
				// Start a thread to handle this client's sesson
				VoteServerThread theThread = new VoteServerThread(myDataSocket, count);
				theThread.start();
				// and go on to the next client
			} // end while forever
		} // end try
		catch (Exception ex) {
			ex.printStackTrace();
		} // end catch
	} // end main
} // end class
