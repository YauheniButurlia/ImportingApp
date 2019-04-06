package com.example.libimport.Readers;

import com.example.libimport.Interfaces.IInfo;
import com.example.libimport.Interfaces.PrimaryInfoReader;
import com.example.libimport.dataTypes.BaseInfo;
import com.example.libimport.dataTypes.Info;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.List;

public class JSONReader implements PrimaryInfoReader {

  @Override
  public List<IInfo> read(String filePath) throws Exception {
    ArrayList<IInfo> info = new ArrayList<>();
    //info.add(new Info("1231231", "https://fif.by/catalog/cp-710", "123"));
    return info;
  }
}
