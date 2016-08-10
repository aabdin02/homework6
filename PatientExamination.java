package homework6;

/**
 * Class to define a patient in examination
 * @author Aeone and Aisha
 *
 */
public class PatientExamination implements Comparable<PatientExamination>{
	
	private double dischargeTime;  
	private ExaminationRoom examRoom; 
	
	public PatientExamination(double dischargeTime, ExaminationRoom examRoom){
		 this.dischargeTime = dischargeTime;
		 this.examRoom = examRoom; 
	}
	
	public double getDischargeTime(){
		return dischargeTime;
	}
	
	public ExaminationRoom getExamRoom(){
		return examRoom; 
	}
	
	// returns the room number we are in 
	public int getRoomNum(){
		return examRoom.getRoomNumber();
	}
	
	// provides comparison
	@Override
	public int compareTo(PatientExamination o) {
		if(o.dischargeTime < dischargeTime){
			return 1;
		}
		return -1;
	}

	public String toString(){
		String toBeAdded = "";
		return toBeAdded + "Discharge Time: " +getDischargeTime()
		+ ", Room Number: " + getRoomNum()+ "\n";
	}
}

