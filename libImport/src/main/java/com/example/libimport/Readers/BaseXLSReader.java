package com.example.libimport.Readers;

import com.example.libimport.Interfaces.IInfo;
import com.example.libimport.Interfaces.PrimaryInfoReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseXLSReader implements PrimaryInfoReader {

  private Class infoClass;

  public BaseXLSReader(Class infoClass){
    this.infoClass = infoClass;
  }

  private List<IInfo> parseDataIntoList(Iterator<Row> iterator) throws Exception{

    List<IInfo> list = new ArrayList<>();
    DataFormatter dataFormatter = new DataFormatter();

    Constructor[] constructors = infoClass.getConstructors();
    Constructor co = constructors[0];
    int parametersCount = co.getParameterCount();
    String[] args = new String[parametersCount];

    while (iterator.hasNext()) {
      Row row = iterator.next();
      if(row.getPhysicalNumberOfCells() < parametersCount){
        throw new Exception("Amount of parameters in [" + co.toString() + "] is not equal to the number of cells in xls file!");
      }
      Iterator<Cell> cellIterator = row.cellIterator();
      Cell cell;
      for(int i = 0; i < args.length; i++){
        cell = cellIterator.next();
        if(cell.getHyperlink()==null){
          args[i] = dataFormatter.formatCellValue(cell);
        } else {
          args[i] = cell.getHyperlink().getAddress();
        }
      }
      list.add((IInfo) co.newInstance(args));
    }
    return list;
  }

  @Override
  public List<IInfo> read(String filePath) throws Exception {
    Workbook workbook = WorkbookFactory.create(new File(filePath));
    Sheet sheet = workbook.getSheetAt(0);

    Iterator<Row> rowIterator = sheet.rowIterator();
    List<IInfo> list = parseDataIntoList(rowIterator);
    workbook.close();
    return list;
  }
}
