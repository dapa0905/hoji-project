package com.example.hoji_project.service.coin;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.hoji_project.model.coin.MarketPriceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CryptoScheduledService {

  private final UpbitFeignClient upbitClient;

  @Scheduled(fixedRate = 1000 * 5) 
  public void fetchCurrencyInfo() {
    List<MarketPriceDTO> response = upbitClient.getCandlesMinutes(1, "KRW-BTC", 1);
    BigDecimal tradePrice = response.get(0).getTradePrice();
    String message = "현재가: " + tradePrice;
    log.info(message);
  }

}
