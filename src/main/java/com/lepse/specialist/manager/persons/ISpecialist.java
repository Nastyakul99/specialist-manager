package com.lepse.specialist.manager.persons;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.lepse.specialist.manager.intervals.DateTimeInterval;
import com.lepse.specialist.manager.intervals.TimeInterval;
import com.lepse.specialist.manager.posts.IPost;

public interface ISpecialist extends IPerson {

	public IPost getPost();//должность
	
	public List<DayOfWeek> getDaysOff();//выходные
	
	public List<TimeInterval> getWorkingTimeIntervals();
	
	public default List<DateTimeInterval> getWorkingDateTimeIntervals(LocalDate start, LocalDate finish) {
		List<DateTimeInterval> result = new ArrayList<>();
		LocalDate date = start;
		while (date.isBefore(finish) || date.equals(finish)) {
			if ( !isDayOff(date) ) {
				result.addAll(TimeInterval.asDateTimeIntervals(getWorkingTimeIntervals(), date));
			}	
			date = date.plusDays(1);
		}
		return result;
	}
	
	public default boolean isDayOff(LocalDate date) {
		return getDaysOff().contains(date.getDayOfWeek());
	}
	
	public default Optional<TimeInterval> findTimeIntervalByStart(LocalTime start) {	
		return getWorkingTimeIntervals()
		.stream().
		filter(i->i.getStart().equals(start))
		.findFirst();
	}
	
	public default boolean isCanMakeAppointment(LocalDateTime date) {
		return ( !isDayOff(date.toLocalDate())
				&& 
				findTimeIntervalByStart(date.toLocalTime()).isPresent());
	}
}
