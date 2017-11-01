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

import java.util.List;

public class Runner {

    private final ExecutionParameters execParameters;

    private int currentCount = 1;

    public Runner(ExecutionParameters execParameters) {
        this.execParameters = execParameters;
    }

    public void monkey() {
        List<EventConfig> events = execParameters.getEvents();
        Emulator emulator = new Emulator(execParameters);
        while (currentCount != execParameters.getEventCount()) {
            int random = emulator.getRandom().nextInt(events.size());
            events.get(random).getEvent().run(emulator);
            currentCount++;
        }

    }
}
