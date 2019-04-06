package com.example.libimport.Interfaces;

import java.io.IOException;
import java.util.List;

public interface IWriter {
  void write(List<IProduct> products, String outputFilePath) throws IOException;
}
