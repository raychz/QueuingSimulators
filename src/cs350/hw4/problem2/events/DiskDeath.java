package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.State;

public class DiskDeath extends Event {

	public DiskDeath(Controller c, State s, double deathTime) {
		super(c, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

}
