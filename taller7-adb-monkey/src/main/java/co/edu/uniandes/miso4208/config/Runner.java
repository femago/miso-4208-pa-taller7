/*
 * @(#)Runner.java
 *
 * Copyright (c) 2017 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Southwest Airlines, Co.
 */
package co.edu.uniandes.miso4208.config;

import co.edu.uniandes.miso4208.event.Context;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Runner {

   private final ExecutionParameters execParameters;

   private int currentCount = 1;

   public Runner(ExecutionParameters execParameters) {
      this.execParameters = execParameters;
   }

   public void monkey() {
      List<EventConfig> events = execParameters.getEvents();
      Context execContext = new Context();
      while (currentCount != execParameters.getEventCount()) {
         int random = ThreadLocalRandom.current().nextInt(0, events.size());
         events.get(random).getEvent().run(execContext);
         currentCount++;
      }

   }
}
