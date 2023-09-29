package com.lepse.specialist.manager.posts;

import java.util.Objects;

public class Post implements IPost {

	private final String designation;
	
	public Post(String designation) {
		this.designation = designation;	
	}

	@Override
	public String getDesignation() {
		return designation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(designation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(designation, other.designation);
	}
}
