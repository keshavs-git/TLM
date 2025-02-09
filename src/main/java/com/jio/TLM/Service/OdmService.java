package com.jio.TLM.Service;

import com.jio.TLM.Entities.Odm;
import com.jio.TLM.Repository.OdmRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class OdmService {

    @Autowired
    OdmRepository odmRepository;

    public void processOdmSheet(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(row.getCell(0).getStringCellValue().equals("")){
                break;
            }
            Odm odm = new Odm();
            odm.setOdm_id(row.getCell(0).getStringCellValue());
            odm.setOdm_name(row.getCell(1).getStringCellValue());
            odm.setOdm_description(row.getCell(2).getStringCellValue());

            this.odmRepository.save(odm);

        }
    }


}
