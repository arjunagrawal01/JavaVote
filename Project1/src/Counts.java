/**
 * 
 * @author Arjun Agrawal
 * Count object to hold all votes. 
 */
public class Counts {
	public static int yesCount = 0;
	public static int noCount = 0;
	public static int dontCareCount = 0;

	public Counts(int yes, int no, int dontCare) {
		yesCount = yes;
		noCount = no;
		dontCareCount = dontCare;
	}
}
