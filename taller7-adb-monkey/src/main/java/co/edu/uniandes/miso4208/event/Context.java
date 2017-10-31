package co.edu.uniandes.miso4208.event;

import lombok.Getter;

public class Context {

   @Getter
   private final Runtime command;

   public Context() {
      command = Runtime.getRuntime();
   }



}