package com.lepse.specialist.manager.intervals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public final class TimeInterval implements Interval<LocalTime> {

	private final LocalTime start;
	
	private final LocalTime finish;
	
	private TimeInterval(LocalTime start, LocalTime finish) throws NullPointerException, IllegalArgumentException {
		if (start==null || finish==null) throw new NullPointerException("start and finish cannot be null");
		if (start.isAfter(finish)) throw new IllegalArgumentException("start cannot be after finish");
		this.start = start;
		this.finish = finish;
	}
	
	public static TimeInterval getInstance(LocalTime start, LocalTime finish) {
		return new TimeInterval(start, finish);
	}
	
	public DateTimeInterval asDateTimeInterval(LocalDate date) {
		LocalDateTime s = LocalDateTime.of(date, start);
		LocalDateTime f = LocalDateTime.of(date, finish);
		return DateTimeInterval.getInstance(s, f);
	}
	
	public static List<DateTimeInterval> asDateTimeIntervals(List<TimeInterval> timeIntervals, LocalDate date) {
		return timeIntervals
				.stream()
				.map(i->i.asDateTimeInterval(date))
				.collect(Collectors.toList());
	}
	
	@Override
	public LocalTime getStart() {
		return start;
	}

	@Override
	public LocalTime getFinish() {
		return finish;
	}

	@Override
	public String toString() {
		return "" + start + " - " + finish;
	}
}
