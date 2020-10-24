package com.wf.training.sba3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wf.training.sba3.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

	@Query("select k from #{#entityName} k where k.interviewName = :interviewName")
	List<Interview> getInterviewByName(@Param("interviewName") String interviewName);
	
	@Query("select k from #{#entityName} k where k.interviewerName = :interviewerName")
	List<Interview> getInterviewsByInterviewer(@Param("interviewerName") String interviewerName);

	@Query("select k from #{#entityName} k where k.interviewerName = :interviewerName and k.interviewName = :interviewName")
	List<Interview> getInterviewByInterviewerandInterviewName(@Param("interviewerName") String interviewerName,
			@Param("interviewName") String interviewName);
}
