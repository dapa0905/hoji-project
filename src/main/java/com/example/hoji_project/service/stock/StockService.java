package com.example.hoji_project.service.stock;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Service;
import com.example.hoji_project.model.stock.StockData;
import jakarta.transaction.Transactional;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
@Transactional
public class StockService implements StockServiceImpl {

  @Override
  public List<StockData> getHistoricalData(String symbol) {

    List<StockData> stockList = new ArrayList<>();
    try {
      Calendar from = Calendar.getInstance();
      from.add(Calendar.MONTH, -1);

      Calendar to = Calendar.getInstance();
      Stock stock = YahooFinance.get(symbol);
      Map<Calendar, yahoofinance.histquotes.HistoricalQuote> history =
          new TreeMap<>(Collections.reverseOrder());

      for (yahoofinance.histquotes.HistoricalQuote quote : stock.getHistory(from, to)) {
        if (quote.getDate() != null && quote.getClose() != null) {
          String date = new SimpleDateFormat("yyyy-MM-dd").format(quote.getDate().getTime());
          stockList.add(new StockData(date, quote.getClose().doubleValue()));
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    stockList.sort(Comparator.comparing(StockData::getDate));
    return stockList;
  }

}
