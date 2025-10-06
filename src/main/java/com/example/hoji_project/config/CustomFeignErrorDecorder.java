package com.example.hoji_project.config;

import java.util.Date;
import com.example.hoji_project.error.ErrorMessages;
import com.example.hoji_project.error.ServiceFailedException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class CustomFeignErrorDecorder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    if (isServerError(response)) {
      handleRetryAfterHeader(response);
      throw new ServiceFailedException(ErrorMessages.EXTERNAL_API_ERROR);
    }
    if (isClientError(response)) {
      throw new ServiceFailedException(ErrorMessages.FAILED_HTTP_REQUEST);
    }
    throw new ServiceFailedException(ErrorMessages.UNKNOWN_ERROR);
  }

  private boolean isClientError(Response response) {
    return response.status() >= 400 && response.status() <= 499;
  }

  private boolean isServerError(Response response) {
    return response.status() >= 500 && response.status() <= 599;
  }

  private void handleRetryAfterHeader(Response response) {
    if (response.headers().containsKey("Retry-After")) {
      String retryAfter = response.headers().get("Retry-After").iterator().next();
      long retryAfterSeconds = Long.parseLong(retryAfter);
      Date retryAfterDate = new Date(System.currentTimeMillis() + (retryAfterSeconds * 1000));
      throw new RetryableException(response.status(), response.reason(),
          response.request().httpMethod(), retryAfterDate, response.request());
    }
  }

}
