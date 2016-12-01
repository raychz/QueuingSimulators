package hw8.problem2;

public class ShuttleSynchSimulation {
	public static final int K = 6;
	public static final int N = 10;
	public static final int numberOfAirportPassengers = 50;

	public static volatile Shuttle shuttle;
	public static volatile Passenger[] passengers = new Passenger[numberOfAirportPassengers];
	public static volatile String[] terminals = new String[] { "A", "B", "C", "D", "E", "T" };

	public static void main(String[] args) {
		int shuttleId = 1000;
		shuttle = new Shuttle(shuttleId);
		shuttle.start();

		for (int i = 0; i < numberOfAirportPassengers; i++) {
			passengers[i] = new Passenger(i);
			passengers[i].start();
		}
	}
}
