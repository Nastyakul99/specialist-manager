package com.lepse.specialist.manager;

import java.time.Duration;
import java.time.LocalDateTime;
import com.lepse.specialist.manager.persons.IPerson;
import com.lepse.specialist.manager.persons.ISpecialist;

public class AppointmentInfo {

	private final ISpecialist specialist;
	
	private final IPerson person;
	
	private final LocalDateTime dateTime;
	
	private final Duration dration;
	
	public AppointmentInfo(ISpecialist specialist,
						   IPerson person,
						   LocalDateTime dateTime,
						   Duration dration) {
		this.specialist = specialist;
		this.person = person;
		this.dateTime = dateTime;
		this.dration = dration;
	}

	@Override
	public String toString() {
		return "AppointmentInfo [specialist=" + specialist
				+ ", person=" + person
				+ ", dateTime=" + dateTime
				+ ", dration=" + dration + "]";
	}
}
