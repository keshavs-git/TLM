package com.jio.TLM.Service;

import com.jio.TLM.Entities.Odm;
import com.jio.TLM.Entities.Project;
import com.jio.TLM.Repository.OdmRepository;
import com.jio.TLM.Repository.ProjectRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class ProjectService {

    @Autowired
    OdmRepository odmRepository;

    @Autowired
    ProjectRepository projectRepository;

    public void processProjectSheet(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(row.getCell(0).getStringCellValue().equals("")){
                break;
            }
            Project project = new Project();
            project.setProject_id(row.getCell(0).getStringCellValue());
            project.setProject_name(row.getCell(1).getStringCellValue());
            project.setProject_description(row.getCell(2).getStringCellValue());

            projectRepository.save(project);
        }
    }

}
