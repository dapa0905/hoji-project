package com.example.hoji_project.error;

public class ServiceFailedException extends RuntimeException {
  public ServiceFailedException(String message) {
      super(message);
  }

  public ServiceFailedException(String message, Throwable cause) {
      super(message, cause);
  }

  public ServiceFailedException(Throwable cause) {
      super(cause);
  }
}
