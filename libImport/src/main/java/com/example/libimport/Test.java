package com.example.libimport;

import com.example.libimport.Interfaces.IInfo;
import com.example.libimport.Readers.BaseXLSReader;
import com.example.libimport.dataTypes.BaseInfo;
import com.example.libimport.dataTypes.FourColonInfo;
import com.example.libimport.dataTypes.Info;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class Test {

  @Retention(RetentionPolicy.RUNTIME)
  public @interface Column{
    String value();
  }

  @Retention(RetentionPolicy.RUNTIME)
  public @interface Link{
    String hyperlink();
  }

  public static void main(String[] args) throws Exception{

    BaseXLSReader xlsReader = new BaseXLSReader(FourColonInfo.class);
    List<IInfo> list = xlsReader.read("/home/yauheni/FW_v2.xls");
    //testInfo(list);
    testFourColonInfo(list);
    //testBaseInfo(list);
  }
  public static void testBaseInfo(List<IInfo> list){
    for(IInfo i : list){
      System.out.println("Info object with its values: " + i.getPath());
    }
  }
  public static void testInfo(List<IInfo> list){
    for(IInfo i : list){
      System.out.println("Info object with its values: " + ((Info)i).getArticle() + " " +
              ((Info)i).getPrice() + " " + ((Info)i).getPath());
    }
  }
  public static void testFourColonInfo(List<IInfo> list){
    FourColonInfo info;
    for(IInfo i : list){
      info = (FourColonInfo) i;
      System.out.println("Info object with its values: " + info.getArticle() + " " +
              info.getPrice().replace(",",".") + " " + info.getPath() + " " + info.getDescription());
    }
  }
}
