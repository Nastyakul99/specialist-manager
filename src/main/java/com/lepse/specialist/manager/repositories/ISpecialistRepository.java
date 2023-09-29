package com.lepse.specialist.manager.repositories;

import java.util.List;

import com.lepse.specialist.manager.persons.ISpecialist;
import com.lepse.specialist.manager.posts.IPost;

public interface ISpecialistRepository {

	public List<ISpecialist> getByPost(IPost post);
	
	public List<ISpecialist> getByLastName(String lastName);
	
}
