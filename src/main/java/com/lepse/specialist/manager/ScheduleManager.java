package com.lepse.specialist.manager;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import com.lepse.specialist.manager.intervals.DateTimeInterval;
import com.lepse.specialist.manager.persons.ISpecialist;
import com.lepse.specialist.manager.repositories.ISpecialistRepository;

public class ScheduleManager extends AScheduleManager {
	
	private final ISpecialistRepository specialistRepository;
	
	public ScheduleManager(ISpecialistRepository specialistRepository) {
		this.specialistRepository = specialistRepository;	
	}

	@Override
	protected void printSchedule(ISpecialist specialist, List<DateTimeInterval> intervals, OutputStream os) {
		write(os, specialist+":");
		intervals.stream()
		.forEach(i->write(os, i.toString()));
	}
	
	private void write(OutputStream os, String s) {
		try {
			os.write((s + "\n").getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			new RuntimeException(e);
		}
	}
	
	@Override
	protected ISpecialistRepository getSpecialistRepository() {
		return specialistRepository;
	}

	@Override
	protected Comparator<ISpecialist> getComparator() {
		return (s1, s2) -> s1.getLastName().compareTo(s2.getLastName());
	}
}
