package com.lepse.specialist.manager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import com.lepse.specialist.manager.intervals.DateTimeInterval;
import com.lepse.specialist.manager.persons.IPerson;
import com.lepse.specialist.manager.persons.ISpecialist;
import com.lepse.specialist.manager.posts.IPost;

public interface IScheduleManager {

	public Map<ISpecialist, List<DateTimeInterval>> getSchedule(LocalDateTime start,
																LocalDateTime finish,
																IPost post);
	
	public List<DateTimeInterval> getSchedule(LocalDateTime start,
											  LocalDateTime finish,
											  ISpecialist specialist);
	
	public boolean isCanMakeAppointment(LocalDateTime date, ISpecialist specialist);
	
	public AppointmentInfo makeAppointment(LocalDateTime date, ISpecialist specialist, IPerson person);
	
}
