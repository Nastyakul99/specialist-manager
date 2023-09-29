package com.lepse.specialist.manager.persons;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.lepse.specialist.manager.intervals.TimeInterval;
import com.lepse.specialist.manager.posts.IPost;

public class Specialist implements ISpecialist {
	 
	private String firstName;
	private String lastName;
	private String patronymic;
	private IPost post;
	private List<TimeInterval> workingIntervals = new ArrayList<>();
	private List<DayOfWeek> daysOff = new ArrayList<>();
	
	private Specialist() {
		
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public String getPatronymic() {
		return patronymic;
	}
	
	@Override
	public IPost getPost() {
		return post;
	}
	
	@Override
	public List<TimeInterval> getWorkingTimeIntervals() {
		//копия
		return new ArrayList<>(workingIntervals);
	}
	
	@Override
	public List<DayOfWeek> getDaysOff() {
		//копия
		return new ArrayList<>(daysOff);
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " " + patronymic
				+ " - " + post.getDesignation();
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, patronymic, post);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specialist other = (Specialist) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(patronymic, other.patronymic) && Objects.equals(post, other.post);
	}
	
	public static class SpecialistBuilder {
		
		private String firstName;
		private String lastName;
		private String patronymic;
		private IPost post;
		private List<TimeInterval> workingIntervals;
		private List<DayOfWeek> daysOff;
		
		public SpecialistBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public SpecialistBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public SpecialistBuilder patronymic(String patronymic) {
			this.patronymic = patronymic;
			return this;
		}
		
		public SpecialistBuilder post(IPost post) {
			this.post = post;
			return this;
		}
		
		public SpecialistBuilder workingIntervals(List<TimeInterval> workingIntervals) {
			//копия
			this.workingIntervals = new ArrayList<>(workingIntervals);
			return this;
		}
		
		public SpecialistBuilder daysOff(List<DayOfWeek> daysOff) {
			//копия
			this.daysOff = new ArrayList<>(daysOff);
			return this;
		}
		
		public Specialist build() throws IllegalArgumentException {	
			if (firstName == null || patronymic == null
				|| lastName == null || post == null) {
				throw new IllegalArgumentException("Отсутствуют "
						+ "обязательные аргументы "
						+ "для создания Specialist");
			}

			Specialist specialist = new Specialist();
			specialist.firstName = firstName;
			specialist.patronymic = patronymic;
			specialist.lastName = lastName;
			specialist.post = post;
			specialist.workingIntervals = workingIntervals;
			specialist.daysOff = daysOff;
			return specialist;
		}
	}
}
