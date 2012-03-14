package rewards.internal.monitor.jamon;

import rewards.internal.monitor.Monitor;
import rewards.internal.monitor.MonitorStatistics;

public class JamonMonitor implements Monitor, MonitorStatistics {

	private com.jamonapi.Monitor monitor;

	public JamonMonitor(com.jamonapi.Monitor monitor) {
		this.monitor = monitor;
	}

	public Monitor start() {
		monitor.start();
		return this;
	}

	public Monitor stop() {
		monitor.stop();
		return this;
	}

	public String getName() {
		return monitor.getLabel();
	}

	public long getCallCount() {
		return (long) monitor.getHits();
	}

	public long getAverageCallTime() {
		return (long) monitor.getAvg();
	}

	public long getLastCallTime() {
		return (long) monitor.getLastValue();
	}

	public long getMaximumCallTime() {
		return (long) monitor.getMax();
	}

	public long getMinimumCallTime() {
		return (long) monitor.getMin();
	}

	public long getTotalCallTime() {
		return (long) monitor.getTotal();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(monitor.getLabel()).append(": ");
		sb.append("Last=").append(monitor.getLastValue()).append(", ");
		sb.append("Calls=").append(monitor.getHits()).append(", ");
		sb.append("Avg=").append(monitor.getAvg()).append(", ");
		sb.append("Total=").append(monitor.getTotal()).append(", ");
		sb.append("Min=").append(monitor.getMin()).append(", ");
		sb.append("Max=").append(monitor.getMax());
		return sb.toString();
	}
}
