import java.rmi.*;
import java.rmi.server.*;

/**
 * This class implements the remote interface HelloInterface.
 * 
 * @author Arjun Agrawal
 */
public class VoteImpl extends UnicastRemoteObject implements VoteInterface {
	private int yes = 0; // yes vote count
	private int no = 0; // no vote count
	private int dc = 0; // don't care vote count
	final String yesQ = "How many yes?";
	final String noQ = "How many no?";
	final String dcQ = "How many don't-care?";

	public VoteImpl() throws RemoteException {
		super();
	}

	public String welcome() throws java.rmi.RemoteException {
		return "Vote (1) yes, (2) no, or (3) don't care";
	}

	public void vote(int vote) throws java.rmi.RemoteException {
		if (vote == 1) {
			yes++;
		} else if (vote == 2) {
			no++;
		} else if (vote == 3) {
			dc++;
		}
	}

	public int checkStatus(String query) throws java.rmi.RemoteException {
		if (query.equals(yesQ)) {
			return yes;
		} else if (query.equals(noQ)) {
			return no;
		} else if (query.equals(dcQ)) {
			return dc;
		} else {
			return -1;
		}
	}

} // end class
