package com.jio.TLM.Service;

import com.jio.TLM.Entities.Part;
import com.jio.TLM.Entities.Project;
import com.jio.TLM.Repository.PartRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class PartService {

    @Autowired
    private PartRepository partRepository;

    public void processPartSheet(Sheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if((int) row.getCell(0).getNumericCellValue() == 0){ //Assuming the partid is never zero and if
//                it is then rows ahead of it wont be scanned.
                break;
            }
            Part part = new Part();
            part.setPart_id((int) row.getCell(0).getNumericCellValue());
            part.setPart_name(row.getCell(1).getStringCellValue());
            part.setPart_description(row.getCell(2).getStringCellValue());

            this.partRepository.save(part);
        }
    }


}
