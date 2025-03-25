package com.jio.TLM;

import com.jio.TLM.Service.ExcelToDatabaseService;

import com.jio.TLM.Service.Project_Odm;
import com.jio.TLM.Service.Project_Part;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.io.IOException;


@SpringBootApplication
public class TlmApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(TlmApplication.class, args);

		String pathFolder = "/Users/keshav_sharma/Desktop/TLM/External resources";
		String pathProjectFile = "/Users/keshav_sharma/Desktop/TLM/External resources/Projects.xlsx";

		ApplicationContext context = SpringApplication.run(TlmApplication.class, args);
		ExcelToDatabaseService service = context.getBean(ExcelToDatabaseService.class);
		service.processExcelFiles(pathFolder);

		Project_Odm project_odm = context.getBean(Project_Odm.class);
		project_odm.projectToOdmMapping(pathProjectFile);

		Project_Part project_Part = context.getBean(Project_Part.class);
		project_Part.assignPartsToProjects(pathProjectFile);


	}

}
