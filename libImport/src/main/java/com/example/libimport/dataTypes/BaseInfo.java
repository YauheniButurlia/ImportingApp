package com.example.libimport.dataTypes;

import com.example.libimport.Interfaces.IInfo;
import com.example.libimport.Test;

public class BaseInfo implements IInfo {
  private String path;

  public BaseInfo(String path){
    this.path = path;
  }
  @Test.Link(hyperlink = "3")
  @Test.Column(value = "1")
  public String getPath(){
    return this.path;
  }
}
