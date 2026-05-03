package com.interview.Interview_Service.Repository;

import com.interview.Interview_Service.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static org.hibernate.boot.model.NamedEntityGraphDefinition.Source.JPA;

@Repository
public interface InterviewRepo extends JpaRepository<Interview, Long> {


}
