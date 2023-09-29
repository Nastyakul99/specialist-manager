package com.lepse.specialist.manager.intervals;

import java.time.LocalDateTime;

public final class DateTimeInterval implements Interval<LocalDateTime> {

	private final LocalDateTime start;
	
	private final LocalDateTime finish;
	
	private DateTimeInterval(LocalDateTime start, LocalDateTime finish) throws NullPointerException, IllegalArgumentException {
		if (start==null || finish==null) throw new NullPointerException("start and finish cannot be null");
		if (start.isAfter(finish)) throw new IllegalArgumentException("start cannot be after finish");
		this.start = start;
		this.finish = finish;
	}
	
	public static DateTimeInterval getInstance(LocalDateTime start, LocalDateTime finish) {
		return new DateTimeInterval(start, finish);
	}
	
	public boolean isInclude(DateTimeInterval dateTimeInterval) {
		return isInclude(dateTimeInterval.start, dateTimeInterval.finish);
	}
	
	public boolean isInclude(LocalDateTime start, LocalDateTime finish) {
		return ((this.start.isAfter(start) || this.start.equals(start))
			&& (this.finish.isBefore(finish) || this.finish.equals(finish)));
	}

	@Override
	public LocalDateTime getStart() {
		return start;
	}

	@Override
	public LocalDateTime getFinish() {
		return finish;
	}
	
	@Override
	public String toString() {
		return "" + start + " - " + finish;
	}
}
