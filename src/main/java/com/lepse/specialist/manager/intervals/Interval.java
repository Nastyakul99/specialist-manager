package com.lepse.specialist.manager.intervals;

import java.time.Duration;
import java.time.temporal.Temporal;

public interface Interval <T extends Temporal> {

	public T getStart();
	
	public T getFinish();
	
	public default Duration getDuration() {
		return Duration.between(getStart(), getFinish());
	}
}
