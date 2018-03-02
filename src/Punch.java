/*
 * Tanner & Nathan
 * Punch.java
 * Mar 2, 2018
 */

public class Punch {

	int terminalid;
	int punchtypeid;
	Badge badge = new Badge(); //Not for sure about this just making the placeholder.
	// ^ This is the minimal information needed to represent the punch.
	// The other properties of this object (such as the ID and the adjusted timestamp) should be initialized to zero or to null, according to their type.
	public Punch(Badge badge, int terminalid, int punchtypeid) {
  		this.terminalid = terminalid;
    	this.punchtypeid = punchtypeid;
    	this.badge = badge; // Not sure if we will pass an int for the badge number here or what; just a placeholder
  	}
}
