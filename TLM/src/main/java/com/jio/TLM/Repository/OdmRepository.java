package com.jio.TLM.Repository;

import com.jio.TLM.Entities.Odm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdmRepository extends JpaRepository<Odm, String> {


}
