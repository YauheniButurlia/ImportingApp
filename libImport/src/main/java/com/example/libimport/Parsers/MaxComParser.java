package com.example.libimport.Parsers;

import com.example.libimport.Interfaces.IInfo;
import com.example.libimport.Interfaces.IProduct;
import com.example.libimport.Interfaces.ProductInfoParser;
import com.example.libimport.dataTypes.BaseInfo;
import com.example.libimport.dataTypes.Info;
import com.example.libimport.dataTypes.Product;

import java.util.ArrayList;
import java.util.List;

public class MaxComParser implements ProductInfoParser {

  List<IProduct> productList;
  @Override
  public List<IProduct> parse(List<IInfo> infoList) throws Exception {
    productList = new ArrayList<>();
    productList.add(new Product("","","","","","2","",""));
    return productList;
  }
}
