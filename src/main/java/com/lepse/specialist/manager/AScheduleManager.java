package com.lepse.specialist.manager;

import java.io.OutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import com.lepse.specialist.manager.intervals.DateTimeInterval;
import com.lepse.specialist.manager.persons.IPerson;
import com.lepse.specialist.manager.persons.ISpecialist;
import com.lepse.specialist.manager.posts.IPost;
import com.lepse.specialist.manager.repositories.ISpecialistRepository;

public abstract class AScheduleManager implements IScheduleManager, IPrintScheduleManager {
	
	protected abstract void printSchedule(ISpecialist specialist, List<DateTimeInterval> intervals, OutputStream os);
	
	protected abstract ISpecialistRepository getSpecialistRepository();
	
	protected abstract Comparator<ISpecialist> getComparator();

	@Override
	public Map<ISpecialist, List<DateTimeInterval>> getSchedule(LocalDateTime start, LocalDateTime finish, IPost post) {
		Map<ISpecialist, List<DateTimeInterval>> res = new TreeMap<>(getComparator());
		
		List<ISpecialist> specialists = getSpecialistRepository().getByPost(post);
		for(ISpecialist specialist : specialists) {
			res.put(specialist, getSchedule(start, finish, specialist));
		}
		return res;
	}
	
	@Override
	public List<DateTimeInterval> getSchedule(LocalDateTime start,
											LocalDateTime finish,
											ISpecialist specialist) {
		return specialist.getWorkingDateTimeIntervals(start.toLocalDate(), finish.toLocalDate())
				.stream()
				.filter(i->i.isInclude(start, finish))
				.collect(Collectors.toList());
	}
	
	@Override
	public void printSchedule(LocalDateTime start, LocalDateTime finish, IPost post, OutputStream os) {
		getSchedule(start, finish, post)
		.entrySet()
		.stream()
		.forEach(s->printSchedule(s.getKey(), s.getValue(), os));	
	}

	@Override
	public boolean isCanMakeAppointment(LocalDateTime date, ISpecialist specialist) {
		return specialist.isCanMakeAppointment(date);
	}

	@Override
	public synchronized AppointmentInfo makeAppointment(LocalDateTime date,
														ISpecialist specialist,
														IPerson person) throws IllegalArgumentException {
		if(isCanMakeAppointment(date, specialist)) {
			Duration duration = specialist.findTimeIntervalByStart(date.toLocalTime()).get().getDuration();
			return new AppointmentInfo(specialist, person, date, duration);
		}
		throw new IllegalArgumentException("Записаться "+date+" к "+specialist+" не возможно");
	}

	@Override
	public void printSchedule(LocalDateTime start, LocalDateTime finish, ISpecialist specialist, OutputStream os) {
		List<DateTimeInterval> schedule = getSchedule(start, finish, specialist);
		printSchedule(specialist, schedule, os);
	}
}
