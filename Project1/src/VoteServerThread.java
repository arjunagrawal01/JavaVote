import java.io.*;

/**
 * 
 * @author Arjun Agrawal
 * Vote server thread to handle new clients, updates counts.
 */
public class VoteServerThread extends Thread {

	static final String endMessage = ".";
	static final String yesMessage = "How many yes?";
	static final String noMessage = "How many no?";
	static final String dontCareMessage = "How many don't-care?";
	Counts count;

	MyStreamSocket myDataSocket;

	public VoteServerThread(MyStreamSocket myDataSocket, Counts count) {
		this.myDataSocket = myDataSocket;
		this.count = count;
	}
	
	public void run() {
		boolean done = false;
		String message;
		try {

			message = myDataSocket.receiveMessage();
			System.out.println("message received: " + message);
			
			
			
			//Query Vote
			myDataSocket.sendMessage("Vote (1):yes, (2):no, (3):don't care: ");
			message = myDataSocket.receiveMessage();
			
			//Update Vote
			if ((message.trim()).equals("1")) {
				Counts.yesCount++;
			} else if ((message.trim()).equals("2")) {
				Counts.noCount++;
			} else if ((message.trim()).equals("3")) {
				Counts.dontCareCount++;
			} else {//End if invalid vote
				System.out.println("error... " + message + "Exiting...");
				System.out.println("Session over.");
				myDataSocket.close();
				done = true;
			}
			
			
			//Query client if needs update on counts. 
			while (!done) {
				myDataSocket.sendMessage("Anything else? Type . to quit: ");
				message = myDataSocket.receiveMessage();
				if ((message.trim()).equals(endMessage)) {
					// Session over; close the data socket.
					System.out.println("Session over.");
					myDataSocket.close();
					done = true;
				} else if ((message.trim()).equals(yesMessage)) {
					myDataSocket.sendMessage(Counts.yesCount + "");
				} else if ((message.trim()).equals(noMessage)) {
					myDataSocket.sendMessage(Counts.noCount + "");
				} else if ((message.trim()).equals(dontCareMessage)) {
					myDataSocket.sendMessage(Counts.dontCareCount + "");
				} else {
					myDataSocket.sendMessage("error... " + message + ", Retry: ");
				} // end else
			} // end while !done
		} // end try
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} // end catch
	} // end run
} // end class
