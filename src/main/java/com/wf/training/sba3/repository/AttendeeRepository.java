package com.wf.training.sba3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wf.training.sba3.entity.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

	@Query(value = "select * from Attendee at where user_id=?1", nativeQuery = true)
	List<Attendee> findAttendeByUserID(Long userId);

	@Query(value = "select * from Attendee at where interview_id = ?1", nativeQuery = true)
	List<Attendee> findAttendeByInterviewID(Long interviewId);

}
