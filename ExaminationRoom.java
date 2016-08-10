package homework6;

/**
 * Examination rooms are ordered based on how busy they are. 
 * @author Aeone & Aisha
 *
 */
	 
public class ExaminationRoom implements Comparable<ExaminationRoom>{
	
	private int roomNumber; 
	private double totalTime; // to track how much time has been spent in this room
	
	public ExaminationRoom(int roomNumber, double totalTime){
		this.roomNumber = roomNumber;
		this.totalTime = totalTime;
	}
	
	public int getRoomNumber(){
		return roomNumber; 
	}
	
	public double getTotalTime(){
		return totalTime; 
	}

	public void setTotalTime(double newTotalTime){
		this.totalTime = newTotalTime; 
	}
	
	@Override
	public int compareTo(ExaminationRoom o) {
		if(o.totalTime > totalTime){
			return 1; 
		}
		return -1; 
	}
	
	public String toString(){
		return "Room Number: " + getRoomNumber() + 
				"\nHow many hours the room has been in use: " + getTotalTime();
	}

}
