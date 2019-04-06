package com.example.libimport.Interfaces;

import com.example.libimport.dataTypes.BaseInfo;

import java.util.List;

public interface ProductInfoParser {
  public List<IProduct> parse(List<IInfo> infoList) throws Exception;
}
