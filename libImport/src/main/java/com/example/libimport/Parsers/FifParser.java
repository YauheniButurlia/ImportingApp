package com.example.libimport.Parsers;

import com.example.libimport.Interfaces.IInfo;
import com.example.libimport.Interfaces.IProduct;
import com.example.libimport.Interfaces.ProductInfoParser;
import com.example.libimport.dataTypes.BaseInfo;
import com.example.libimport.dataTypes.FourColonInfo;
import com.example.libimport.dataTypes.Info;
import com.example.libimport.dataTypes.Product;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple example, used on the jsoup website.
 */
public class FifParser implements ProductInfoParser {

  List<IProduct> productList;

  public List<IProduct> parse(List<IInfo> infoList)  throws Exception, IOException, InterruptedException{
    if(infoList == null) {
      throw new Exception("infoList can't be null.");
    }
    productList = new ArrayList<>();
    Document doc;
    int i=0;
    for(IInfo info : infoList) {

      System.out.println(i++);
      System.out.println(info.getPath());

      doc = Jsoup.connect(info.getPath()).get();

      Element shortDescription = doc.select("div[data-tab='1'].tovar_tab").first();
      String sd = shortDescription.html();

      Element fullDescription = doc.select("div[data-tab='0'].tovar_tab").first();
      String fd = fullDescription.html();

      Element schemeImage = doc.select("div[data-tab='2'].tovar_tab img").first();
      String schemeImagePath = "";
      if(schemeImage != null) {
        schemeImagePath = schemeImage.absUrl("src");
      }

      Element title = doc.select("title").first();
      String productName = title.text()
                                .replace(" | Евроавтоматика ФиФ", "")
                                .replace(" | Евроавтоматика ФиФ","");


      Element category = doc.select("div.breadcrumbs a").last();
      String productCategory = category.text();

      Element imagePath = doc.select("div.tovar_img img").first();
      String imageP = "";
      if(imagePath != null){
        imageP = imagePath.absUrl("src");
      }

      String price = ((Info) info).getPrice().replace(",",".");
      String article = ((Info) info).getArticle();

      productList.add(new Product(article, productName, productCategory, sd, imageP, price, fd, schemeImagePath));
    }
    return productList;
  }
}
