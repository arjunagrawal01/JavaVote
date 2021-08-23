
// A simple RMI interface file - Arjun Agrawal
import java.rmi.*;

/**
 * This is a remote interface.
 * 
 * @author M. L. Liu
 */

public interface VoteInterface extends Remote {
	/**
	 * This remote method returns a message.
	 * 
	 * @param name - a String containing a name.
	 * @return a String message.
	 */

	public String welcome() throws java.rmi.RemoteException;

	public void vote(int vote) throws java.rmi.RemoteException;

	public int checkStatus(String query) throws java.rmi.RemoteException;

} // end interface


