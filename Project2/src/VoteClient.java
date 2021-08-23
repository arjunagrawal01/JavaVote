import java.io.*;
import java.rmi.*;

/**
 * This class represents the object client for a distributed object of class
 * Hello, which implements the remote interface HelloInterface.
 * 
 * @author Arjun Agrawal
 */
public class VoteClient {

	public static void main(String args[]) {
		try {

			int RMIPort;
			String hostName;
			InputStreamReader is = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
			System.out.println("Enter the RMIRegistry host namer:");
			hostName = br.readLine();
			System.out.println("Enter the RMIregistry port number:");
			String portNum = br.readLine();
			RMIPort = Integer.parseInt(portNum);
			String registryURL = "rmi://" + hostName + ":" + portNum + "/hello";
			// find the remote object and cast it to an interface object
			VoteInterface h = (VoteInterface) Naming.lookup(registryURL);
			System.out.println("Lookup completed ");
			
			
			
			System.out.println(h.welcome());
			h.vote(Integer.parseInt(br.readLine()));
			while (true) {
				System.out.println("Anything else? Type '.' to quit");
				String response = br.readLine();
				if (response.equals(".")) {
					break;
				} else {
					System.out.println(h.checkStatus(response));
				}
			}
		} // end try
		catch (Exception e) {
			System.out.println("Exception in VoteClient: " + e);
		}
	} // end main
}// end class