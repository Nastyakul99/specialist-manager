package com.lepse.specialist.manager;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.lepse.specialist.manager.intervals.TimeInterval;
import com.lepse.specialist.manager.persons.ISpecialist;
import com.lepse.specialist.manager.persons.Specialist;
import com.lepse.specialist.manager.posts.Posts;
import com.lepse.specialist.manager.repositories.SpecialistRepositoryImpl;

public class SpecialistManagerTests {

	
	@Test
	public void getScheduleTest() {
		SpecialistRepositoryImpl repo = newSpecialistRepositoryImpl();
		
		IScheduleManager manager = new ScheduleManager(repo);
		
		((IPrintScheduleManager) manager).printSchedule(LocalDateTime.of(2023, 9, 29, 12, 0),
				LocalDateTime.of(2023, 10, 2, 12, 0),
				Posts.DOCTOR,
				System.out);
		
		Assert.assertTrue(manager.isCanMakeAppointment(LocalDateTime.of(2023, 10, 2, 12, 0),
				repo.getByLastName("Карина").get(0)));
		
		//записываюсь в выходной
		Assert.assertFalse(manager.isCanMakeAppointment(LocalDateTime.of(2023, 10, 1, 12, 0),
				repo.getByLastName("Карина").get(0)));
		
		//записываюсь вне рабочего интервала (18:00)
		Assert.assertFalse(manager.isCanMakeAppointment(LocalDateTime.of(2023, 10, 2, 18, 0),
				repo.getByLastName("Карина").get(0)));
		
	}
	
	private SpecialistRepositoryImpl newSpecialistRepositoryImpl() {
		SpecialistRepositoryImpl repo = new SpecialistRepositoryImpl();
		
		List<TimeInterval> intervals1 = new ArrayList<>();
		intervals1.add(TimeInterval.getInstance(LocalTime.of(8, 0) , LocalTime.of(8, 40)));
		intervals1.add(TimeInterval.getInstance(LocalTime.of(9, 0) , LocalTime.of(9, 40)));
		intervals1.add(TimeInterval.getInstance(LocalTime.of(12, 0) , LocalTime.of(12, 40)));
		intervals1.add(TimeInterval.getInstance(LocalTime.of(13, 0) , LocalTime.of(13, 40)));
		
		
		ISpecialist s1 = new Specialist.SpecialistBuilder()
						.firstName("Лариса")
						.lastName("Карина")
						.patronymic("Николаевна")
						.post(Posts.DOCTOR)
						.workingIntervals(intervals1)
						.daysOff(Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY))
						.build();
		
		ISpecialist s2 = new Specialist.SpecialistBuilder()
				.firstName("Анастасия")
				.lastName("Куликова")
				.patronymic("Владимировна")
				.post(Posts.STYLIST)
				.workingIntervals(intervals1)
				.daysOff(Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY))
				.build();
		
		ISpecialist s3 = new Specialist.SpecialistBuilder()
				.firstName("Андрей")
				.lastName("Аверин")
				.patronymic("Николаев")
				.post(Posts.DOCTOR)
				.workingIntervals(intervals1)
				.daysOff(Arrays.asList(DayOfWeek.SUNDAY))
				.build();
		
		repo
		.addSpecialist(s2)
		.addSpecialist(s1)
		.addSpecialist(s3);
		
		return repo;
	}
	
}
