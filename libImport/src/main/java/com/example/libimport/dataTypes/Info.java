package com.example.libimport.dataTypes;

import com.example.libimport.Interfaces.IInfo;
import com.example.libimport.Test;

import org.apache.poi.util.Internal;

public class Info implements IInfo {
  private String article;
  private String price;
  private String path;

  public Info(String article, String path, String price){
    this.path = path;
    this.article = article;
    this.price = price;
  }

  @Test.Column(value = "1")
  public String getArticle() {
    return article;
  }

  public String getPath() {
    return path;
  }

  public String getPrice() {
    return price;
  }

  @Test.Link(hyperlink = "222222")
  public void sayWord(String word){
    this.article = word;
  }
}
