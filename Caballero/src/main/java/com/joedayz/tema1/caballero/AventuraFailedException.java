package com.joedayz.tema1.caballero;

@SuppressWarnings("serial")
public class AventuraFailedException extends Exception {
  public AventuraFailedException() {}
  
  public AventuraFailedException(String mensaje) {
    super(mensaje);
  }
}
