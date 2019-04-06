package com.example.libimport.Readers;

import com.example.libimport.Interfaces.IInfo;
import com.example.libimport.Interfaces.PrimaryInfoReader;
import com.example.libimport.dataTypes.BaseInfo;
import com.example.libimport.dataTypes.FourColonInfo;
import com.example.libimport.dataTypes.Info;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader implements PrimaryInfoReader {

  public List<IInfo> read(String xls_file_path) throws IOException, InvalidFormatException{

    List<IInfo> infoList = new ArrayList<>();

    Workbook workbook = WorkbookFactory.create(new File(xls_file_path));
    Sheet sheet = workbook.getSheetAt(0);

    DataFormatter dataFormatter = new DataFormatter();

    Iterator<Row> rowIterator = sheet.rowIterator();
    while (rowIterator.hasNext()) {
      Row row = rowIterator.next();

      // Now let's iterate over the columns of the current row
      Iterator<Cell> cellIterator = row.cellIterator();

      Cell cell = cellIterator.next();
      String article = dataFormatter.formatCellValue(cell);
      cell = cellIterator.next();
      String path = cell.getHyperlink().getAddress();
      cell = cellIterator.next();
      String price = dataFormatter.formatCellValue(cell).replace(",", ".");
      //TODO how could I do it in another way?

      //infoList.add(new Info(article, path.replace("http://","https://"), price));
      infoList.add(new FourColonInfo(article,path.replace("http://","https://"), price,"hi"));
      //TODO category thing
    }
    workbook.close();
    return infoList;
  }
}

