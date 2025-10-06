package com.example.hoji_project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.hoji_project.model.stock.StockData;
import com.example.hoji_project.service.stock.StockService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
  private final StockService stockService;

  @GetMapping("/{symbol}")
  public List<StockData> getStockData(@PathVariable("symbol") String symbol) {
    try {
      return stockService.getHistoricalData(symbol.toUpperCase());
    } catch (Exception  e) {
      if(e.getMessage().contains("429")) {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
        return stockService.getHistoricalData(symbol.toUpperCase());
      }
      throw e;
    }
  }

  @GetMapping("/stocks")
  public String showStockPage() {
    return "stock/stocks";
  }

}
