package com.example.libimport.Writers;

import com.example.libimport.Interfaces.IProduct;
import com.example.libimport.Interfaces.IWriter;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WriterToCSV implements IWriter {

  String[] headerRecords;

  public WriterToCSV(String[] headerRecords){
    this.headerRecords = headerRecords;
  }

  public void write(List<IProduct> products, String csv_file_path) throws IOException {
    try (
            Writer writer = Files.newBufferedWriter(Paths.get(csv_file_path));

            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.DEFAULT_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END)

    ) {
      if(isHeaderRecordsSet()){
        csvWriter.writeNext(headerRecords);
      }
      for(IProduct p: products) {
        csvWriter.writeNext(p.toStringArray());
      }
    }
  }

  private boolean isHeaderRecordsSet(){
    return headerRecords != null;
  }
}
