package com.lepse.specialist.manager.posts;

public enum Posts implements IPost {

	DOCTOR("Врач"),
	STYLIST("Стилист"),
	MANICURIST("Мастер маникюра");
	
	private final String designation;
	
	private Posts(String designation) {
		this.designation=designation;
	}
	
	@Override
	public String getDesignation() {
		return designation;
	}
}
