package com.example.libimport.Interfaces;

import com.example.libimport.dataTypes.BaseInfo;
import com.example.libimport.dataTypes.Info;

import java.util.List;

public interface PrimaryInfoReader {
  List<IInfo> read(String filePath) throws Exception;
  //void setPrimaryInfo(IInfo infoType);
}
