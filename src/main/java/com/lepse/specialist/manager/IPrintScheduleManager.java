package com.lepse.specialist.manager;

import java.io.OutputStream;
import java.time.LocalDateTime;
import com.lepse.specialist.manager.persons.ISpecialist;
import com.lepse.specialist.manager.posts.IPost;

public interface IPrintScheduleManager {

	public void printSchedule(LocalDateTime start,
			                  LocalDateTime finish,
			                  IPost post,
			                  OutputStream os);
	
	public void printSchedule(LocalDateTime start,
							  LocalDateTime finish,
			                  ISpecialist specialist,
			                  OutputStream os);
}
