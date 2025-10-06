package com.example.hoji_project.service.coin;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hoji_project.model.coin.MarketPriceDTO;

@FeignClient(name = "UpbitCurrency", url = "https://api.upbit.com/v1")
public interface UpbitFeignClient {

  @GetMapping("/candles/minutes/{unit}")
  List<MarketPriceDTO> getCandlesMinutes(@PathVariable("unit") int unit,
      @RequestParam("market") String market, @RequestParam("count") int count);

}
