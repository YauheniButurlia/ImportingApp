package com.example.libimport;

import com.example.libimport.Interfaces.IInfo;
import com.example.libimport.Interfaces.IProduct;
import com.example.libimport.Interfaces.IWriter;
import com.example.libimport.Interfaces.PrimaryInfoReader;
import com.example.libimport.Interfaces.ProductInfoParser;
import com.example.libimport.Parsers.FifParser;
import com.example.libimport.Parsers.MaxComParser;
import com.example.libimport.Readers.BaseXLSReader;
import com.example.libimport.Writers.WriterToCSV;
import com.example.libimport.dataTypes.Info;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

public class ImportProgram {

  private PrimaryInfoReader infoReader;
  private ProductInfoParser infoParser;
  private IWriter infoWriter;

  private List<IInfo> infoList;
  private List<IProduct> productList;

/*
  private static String FILE_NAME = "FW";
  private static String XLS_PATH = "/home/yauheni/" + FILE_NAME + ".xls";
  private static String CSV_PATH = "/home/yauheni/" + FILE_NAME + ".csv";
*/
  private static String[] HEADER_RECORDS =  new String[]{"Артикул", "Имя", "Категории", "Короткое описание", "Изображения", "Базовая цена", "Описание"};

  public static void main(String[] args) throws Exception, IOException, InvalidFormatException, InterruptedException {

    PrimaryInfoReader iReader;
    ProductInfoParser iParser = null;

    String XLS_PATH = "";
    if(args.length < 2){
      usage();
    } else if (args.length == 2){
      if(args[0].equals("-fif")){
        iParser = new FifParser();
      } else if(args[0].equals("-maxcom")){
        iParser = new MaxComParser();
      } else {
        usage();
      }
      XLS_PATH = args[1];
    } else {
      usage();
    }
    String CSV_PATH = XLS_PATH.replace(".xls", ".csv");


    new ImportProgram().setInfoReader(new BaseXLSReader(Info.class))
                       .setInfoParser(iParser) //new FifParser()
                       .setInfoWriter(new WriterToCSV(HEADER_RECORDS))
                       .run(XLS_PATH, CSV_PATH);


  }

  private static void usage(){
    System.out.println("This program helps extracting information about products from table-like files and");
    System.out.println("converting it into a CSV file for automated importing tool in WooCommerce plugin.");
    System.out.println();
    System.out.println("The program expecting user to determine a host from where the Parser should parse product information.");
    System.out.println();
    System.out.println("First parameter is expected to be either '-fif' or '-maxcom' to determine which parser to use.");
    System.out.println();
    System.out.println("Second one comes to the absolute file path of info-table with links on products pages.");
    System.out.println();
    System.out.println("The current version of the program expecting you to use the following cells order in XLS files:");
    System.out.println("Article | Path | Price");
    System.out.println();
    System.out.println("Wish u have fun!");
    System.exit(1);
  }

  public ImportProgram(){

  }

  public ImportProgram setInfoReader(PrimaryInfoReader reader){
    this.infoReader = reader;
    return this;
  }
  public ImportProgram setInfoParser(ProductInfoParser parser){
    this.infoParser = parser;
    return this;
  }
  public ImportProgram setInfoWriter(IWriter writer){
    this.infoWriter = writer;
    return this;
  }

  public void run(String inputFilePath, String outputFilePath) throws Exception{
    if(infoReader == null){
      throw new Exception("PrimaryInfoReader is not set.");
    }
    if(infoParser == null){
      throw new Exception("ProductInfoParser is not set.");
    }
    if(infoWriter == null){
      throw new Exception("IWriter is not set.");
    }
    infoList = infoReader.read(inputFilePath);
    productList = infoParser.parse(infoList);
    infoWriter.write(productList, outputFilePath);
  }

  private static void addMaxComSSL() {
    System.setProperty("javax.net.ssl.trustStore", "/home/yauheni/Desktop/wwwmaxcomby.jks");
  }
}
