package com.example.libimport.dataTypes;

import com.example.libimport.Interfaces.IProduct;

public class Product implements IProduct {
  private String pArticle;
  private String pName;
  private String pCategory;
  private String shortDescription;
  private String pImagePath;
  private String pPrice;
  private String pSchemeImagePath;
  private String fullDescription;

  public Product(String pArticle, String pName, String pCategory,
                 String shortDescription, String pImagePath, String pPrice,
                 String fullDescription, String pSchemeImagePath) {
    this.pArticle = pArticle;
    this.pName = pName;
    this.pCategory = pCategory;
    this.shortDescription = shortDescription;
    this.pImagePath = pImagePath;
    this.pPrice = pPrice;
    this.fullDescription = fullDescription;
    this.pSchemeImagePath = pSchemeImagePath;
  }


  public String getArticle() {
    return pArticle;
  }

  public String getName() {
    return pName;
  }

  public String getCategory() {
    return pCategory;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public String getImagePath() {
    return pImagePath;
  }

  public String getPrice() {
    return pPrice;
  }

  public String getFullDescription() {
    return fullDescription;
  }

  public String getSchemeImagePath() {
    return pSchemeImagePath;
  }

  public String[] toStringArray() {
    String imagePath =  this.pImagePath;
    if(!this.pSchemeImagePath.equals("")){
      imagePath = this.pImagePath + " , " + this.pSchemeImagePath;
    }

    return new String[]{this.pArticle, this.pName, this.pCategory,
            this.shortDescription, imagePath,
            this.pPrice, this.fullDescription};
  }
}
