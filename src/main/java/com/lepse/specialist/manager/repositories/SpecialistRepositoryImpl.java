package com.lepse.specialist.manager.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.lepse.specialist.manager.persons.ISpecialist;
import com.lepse.specialist.manager.posts.IPost;

public class SpecialistRepositoryImpl implements ISpecialistRepository {
	
	private List<ISpecialist> specialists = new ArrayList<>();
	
	public SpecialistRepositoryImpl() {
		
	}
	
	public SpecialistRepositoryImpl(List<ISpecialist> specialists) {
		this.specialists = specialists == null ? new ArrayList<>() : specialists;
	}

	@Override
	public List<ISpecialist> getByPost(IPost post) {
		return specialists.stream()
		.filter(s->s.getPost().equals(post))
		.collect(Collectors.toList());
	}

	@Override
	public List<ISpecialist> getByLastName(String lastName) {
		return specialists.stream()
		.filter(s->s.getLastName().equals(lastName))
		.collect(Collectors.toList());
	}
	
	public SpecialistRepositoryImpl addSpecialist(ISpecialist specialist) {
		this.specialists.add(specialist);
		return this;
	}
}
