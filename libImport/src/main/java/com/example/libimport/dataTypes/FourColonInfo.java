package com.example.libimport.dataTypes;

public class FourColonInfo extends BaseInfo {
  private String article;
  private String price;
  private String description;

  public FourColonInfo(String article, String path, String price, String description) {
    super(path);
    this.article = article;
    this.price = price;
    this.description = description;
  }

  public String getArticle() {
    return article;
  }

  public String getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }
}
