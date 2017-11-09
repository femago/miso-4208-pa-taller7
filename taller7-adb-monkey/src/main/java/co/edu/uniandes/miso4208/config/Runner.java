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

import lombok.extern.java.Log;

import java.util.List;
import java.util.Scanner;

import static co.edu.uniandes.miso4208.config.Emulator.ADB;

@Log
public class Runner {

    private final ExecutionParameters execParameters;
    private final Emulator emulator;

    private int currentCount = 0;


    public Runner(ExecutionParameters execParameters, Emulator emulator) {
        this.execParameters = execParameters;
        this.emulator = emulator;
    }

    public void monkey() {
        List<EventConfig> events = execParameters.getEvents();
        while (currentCount < execParameters.getEventCount()) {
            int random = emulator.getRandom().nextInt(events.size());
            events.get(random).getEvent().run(emulator);
            currentCount++;
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                log.severe(e.getMessage());
            }
        }

    }

    public void installApp() {
        try {
            String adbCmd = ADB + " install \"" + execParameters.getApkLocation() + "\"";
            Process proc = Runtime.getRuntime().exec(adbCmd);
            log.info("Instalando APK");
            Scanner s = new Scanner(proc.getInputStream()).useDelimiter("\n");
            Thread.sleep(500);
            while (proc.isAlive()) {
                while (s.hasNext())
                    log.info(s.next());
                Thread.sleep(1000);
            }
            if (proc.exitValue() != 0) {
                throw new RuntimeException("APK no pudo ser instalado");
            }
            log.info("APK instalado");
        } catch (Exception e) {
            throw new RuntimeException("Error instalando APK", e);
        }
    }

    public void startApp() {
        try {
            String adbCmd = ADB + " shell monkey -p " + execParameters.getApkName() + " -c android.intent.category.LAUNCHER 1";
            Process proc = Runtime.getRuntime().exec(adbCmd);
            log.info("Abriendo APK");
            Scanner s = new Scanner(proc.getInputStream()).useDelimiter("\n");
            Thread.sleep(500);
            while (proc.isAlive()) {
                while (s.hasNext())
                    log.info(s.next());
                Thread.sleep(1000);
            }
            log.info("APK iniciado");
        } catch (Exception e) {
            throw new RuntimeException("Error iniciando APK", e);
        }
    }
}
