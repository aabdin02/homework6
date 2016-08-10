package homework6;

/**
 * A class to describe the visit of a patient, defined by the arrival time, the
 * urgency of the visit, and the length of the visit.
 */
public class PatientVisit implements Comparable<PatientVisit>{

	private double arrivalTime; // in minutes

	private int urgency; // 1 to 10 (1 is the most urgent)

	private double duration; // duration of the visit in minutes

	/**
	 * Creates a patient visit given its characteristics
	 * 
	 * @param theArrivalTime
	 *            the time of the arrival (in minutes)
	 * @param theUrgency
	 *            the urgency level (1 to 10, 1 being most urgent)
	 * @param theDuration
	 *            the length of the patient visit (in minutes)
	 */
	public PatientVisit(double theArrivalTime, int theUrgency, double theDuration) {
		arrivalTime = theArrivalTime;
		urgency = theUrgency;
		duration = theDuration;
	}

	/**
	 * Returns the arrival time (in minutes)
	 * @return the arrival time (in minutes)
	 */
	public double getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Returns the duration of the visit (in minutes)
	 * @return the duration of the visit (in minutes)
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * Returns the urgency of the visit (1 to 10, 1 being most urgent)
	 * 
	 * @return the urgency of the visit
	 */
	public int getUrgency() {
		return urgency;
	}


	@Override
	public int compareTo(PatientVisit justEntered) {
		if (justEntered.getUrgency() < getUrgency()) {// more urgent.
			return 1;
		}else if(justEntered.getUrgency()==getUrgency()){
			if(justEntered.getArrivalTime()<getArrivalTime()){
				return 1;
			}else{
				return -1;
			}
		}

		return -1;
	}
	
//We need this because we only get the address otherwise.
	public String toString(){
		String adding = " ";
		return adding +"Arrival Time: " + getArrivalTime() +" " + 
				"Urgency:"+ getUrgency(); 
	}
}
