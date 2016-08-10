package homework6;


import java.util.Scanner;

/**
 * Simulation and Main Method
 * 
 * @author Aeone & Aisha
 *
 */
public class Simulation {
	private Scanner scanner = new Scanner(System.in);

	private PriorityQueue<PatientVisit> patientQueue = new PriorityQueue<PatientVisit>();
	private PriorityQueue<PatientExamination> examinationQueue = new PriorityQueue<PatientExamination>();
	private PriorityQueue<ExaminationRoom> roomQueue = new PriorityQueue<ExaminationRoom>();

	private int roomNumber = 0;

	public static void main(String[] args) {
		run();
	}

	public static void run() {

		// to check if there are rooms available
		boolean roomAvailable = false;

		// the current time, in hours
		double currentTime = 0;

		Simulation simulation = new Simulation();

		// ask for user input for how many rooms
		System.out.println("How many rooms in the hospital? ");

		int number_of_rooms = simulation.scanner.nextInt() - 1; // count starts
																// at 0
		int copy_of_rooms = number_of_rooms;
		
		int numPatients = 0; 

		// ask for user to choose simulation type
		System.out.println("Do you want RandomGenerated patients or not: Yes or No");
		String answer = simulation.scanner.next();

		// total time room has been in use is 0
		double totalTime = 0;

		// populates the room queue with empty rooms
		while (number_of_rooms >= 0) {
			simulation.roomNumber++;
			ExaminationRoom availableRoom = new ExaminationRoom(simulation.roomNumber, 0);
			simulation.roomQueue.insert(availableRoom);
			roomAvailable = true;
			number_of_rooms--;
		}

		// runs the simulation based on the current time
		// increments in minutes
		while (currentTime <= 600) {
			PatientVisit patient;
			if (answer.toLowerCase().equals("no")) {
				patient = PatientVisitGenerator.getNextProgrammedArrival(currentTime, copy_of_rooms);
				numPatients ++;
			} // random generator
			else {
				patient = PatientVisitGenerator.getNextRandomArrival(currentTime);
				numPatients ++; 
			}
			currentTime++;

			// when patient arrives insert in patientQueue
			simulation.patientQueue.insert(patient);
			System.out.println("\n-----\n");
			System.out.println("The time is now: " + (int)currentTime/60 + ":" + (int)currentTime%60);
			System.out.println("A patient has arrived.\nThere are currently " + simulation.patientQueue.getSize()
					+ " patients in the waiting room.");
			
			if (roomAvailable && !simulation.roomQueue.isEmpty()) {
				PatientVisit currentPatient = simulation.patientQueue.front();
				// calculate the discharge time.
				double dischargeTime = currentPatient.getArrivalTime() + currentPatient.getDuration();

				// get room at the front of the queue
				// presumably the least visited room
				ExaminationRoom currentRoom = simulation.roomQueue.front();
				totalTime = currentRoom.getTotalTime() + currentPatient.getDuration();
				currentRoom.setTotalTime(totalTime);

				// put patient in the least visited room at the front of the
				// room queue
				PatientExamination patientExamined = new PatientExamination(dischargeTime,
						simulation.roomQueue.front());

				// dequeue the patient from patientQueue and
				// put them in the examinationQueue and remove an empty room
				// from the room Queue
				simulation.patientQueue.remove();
				simulation.roomQueue.remove(); // removes from the front
				simulation.examinationQueue.insert(patientExamined);
				System.out.println(
						"There are currently " + simulation.roomQueue.getSize() + " available rooms in the system.");
				System.out.println(
						"There are currently " + simulation.examinationQueue.getSize() + " patients being examined");
			}
			// discharged patient means empty room
			// triggers patient discharge
			if (simulation.examinationQueue.front().getDischargeTime() >= currentTime) {
				ExaminationRoom availableRoom = simulation.examinationQueue.front().getExamRoom();
				simulation.roomQueue.insert(availableRoom);
				simulation.examinationQueue.remove();
				System.out.println("A patient has been discharged.\nThere are currently "
						+ simulation.roomQueue.getSize() + " available rooms in the system.");
				System.out.println(
						"There are currently " + simulation.examinationQueue.getSize() + " patients being examined");
				roomAvailable = true; // trigger available room flag
			}
		}
		System.out.println("Number of patients treated was: " + numPatients);
	}
}
