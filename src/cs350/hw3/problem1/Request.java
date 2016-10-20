package cs350.hw3.problem1;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW3.1: M/M/1 Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class Request {
	private double IAT;
	private double Ts;
	private double arrival;
	private double start;
	private double end;
	private double Tq;
	private double Tw;

	public double getIAT() {
		return IAT;
	}

	public void setIAT(double IAT) {
		this.IAT = IAT;
	}

	public double getTs() {
		return Ts;
	}

	public void setTs(double ts) {
		Ts = ts;
	}

	public double getArrival() {
		return arrival;
	}

	public void setArrival(double arrival) {
		this.arrival = arrival;
	}

	public double getStart() {
		return start;
	}

	public void setStart(double start) {
		this.start = start;
	}

	public double getEnd() {
		return end;
	}

	public void setEnd(double end) {
		this.end = end;
	}

	public double getTq() {
		return Tq;
	}

	public void setTq(double tq) {
		Tq = tq;
	}

	public double getTw() {
		return Tw;
	}

	public void setTw(double tw) {
		Tw = tw;
	}

	@Override
	public String toString() {
		return IAT + "," + Ts + "," + arrival + "," + start + "," + end + "," + Tq + "," + Tw;
	}

}
