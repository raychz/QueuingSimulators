package cs350.hw4.problem2.events;

import cs350.hw4.problem2.Controller;
import cs350.hw4.problem2.MM1System;
import cs350.hw4.problem2.MM2System;
import cs350.hw4.problem2.QueuingSystem;
import cs350.hw4.problem2.State;
import cs350.hw4.problem2.utilities.RandomGenerator;

/**
 * <p>
 * Boston University CS350
 * <p>
 * HW4.2: Multi-Server Network Simulator
 * 
 * @author Raymond Chavez {@literal <rchavez9@bu.edu>}
 */
public class Monitor extends Event {
	private double startTime;
	private final MM2System CPU;
	private final MM1System disk;
	private final MM1System network;

	public Monitor(Controller c, State s) {
		super(c, s);
		this.CPU = s.getCPU();
		this.disk = s.getDisk();
		this.network = s.getNetwork();
		
		/*
		 * We must start performing monitors after the warm up period has
		 * completed
		 */
		startTime = c.getCurrentTime() == 0 ? s.getSimulationTime()
				: c.getCurrentTime() + RandomGenerator.genExpRV(2 * CPU.getLambda());
	}

	@Override
	public void exec() {
		s.logger.numSysMonitors++;

		// Schedule new monitor event
		c.addEvent(new Monitor(c, s));
		
		// Inspect the CPU
		int isCPU1Busy = CPU.isCPU1Busy() ? 1 : 0;
		int isCPU2Busy = CPU.isCPU2Busy() ? 1 : 0;
		int curCPUQ = CPU.requestQueue.size() + isCPU1Busy + isCPU2Busy;
		int curCPUW = CPU.requestQueue.size();
		double curCPURho = (isCPU1Busy + isCPU2Busy) / 2;
		s.logger.CPUWTotal += curCPUW;
		s.logger.CPUQTotal += curCPUQ;
		s.logger.CPURhoTotal += curCPURho;
		
		// Inspect the Disk
		int isDiskBusy = disk.isMM1ServerBusy() ? 1 : 0;
		int curDiskQ = disk.requestQueue.size() + isDiskBusy;
		int curDiskW = disk.requestQueue.size();
		double curDiskRho = isDiskBusy;
		s.logger.DiskWTotal += curDiskW;
		s.logger.DiskQTotal += curDiskQ;
		s.logger.DiskRhoTotal += curDiskRho;
		
		// Inspect the Network
		int isNetworkBusy = network.isMM1ServerBusy() ? 1 : 0;
		int curNetworkQ = network.requestQueue.size() + isNetworkBusy;
		int curNetworkW = network.requestQueue.size();
		double curNetworkRho = isNetworkBusy;
		s.logger.NetworkWTotal += curNetworkW;
		s.logger.NetworkQTotal += curNetworkQ;
		s.logger.NetworkRhoTotal += curNetworkRho;
	}

	@Override
	public double getTime() {
		return this.startTime;
	}
}