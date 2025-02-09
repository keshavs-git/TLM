package com.jio.TLM.Repository;

import com.jio.TLM.Entities.Part;
import com.jio.TLM.Entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {

//    List<Part> findByProject(Project project);

}
