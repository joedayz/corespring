package com.joedayz.tema1.caballero;

@SuppressWarnings("serial")
public class GrialNotFoundException extends AventuraFailedException {
  public GrialNotFoundException() {}
  
  public GrialNotFoundException(String message) {
    super(message);
  }
}
