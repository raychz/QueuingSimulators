package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.Request;
import cs350.hw4.problem2.utilities.RandomGenerator;

public class DiskBirth extends Event {
	private Request r = new Request();
	private MM1System m;

	public DiskBirth(Controller c, MM1System m) {
		super(c);
		this.m = m;
		r.setIAT(RandomGenerator.genExpRV(m.lambda));
		r.setArrival(c.getCurrentTime() + r.getIAT());
		r.setTs(RandomGenerator.genExpRV(1.0 / m.Ts));
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
