package com.example.hoji_project.service.stock;

import java.util.List;
import com.example.hoji_project.model.stock.StockData;

public interface StockServiceImpl {
  
  List<StockData> getHistoricalData(String symbol);

}
