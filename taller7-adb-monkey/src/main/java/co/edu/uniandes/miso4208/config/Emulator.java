package co.edu.uniandes.miso4208.config;

import co.edu.uniandes.miso4208.config.ExecutionParameters;
import co.edu.uniandes.miso4208.util.ExceptionUtil;
import lombok.Getter;
import lombok.extern.java.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Log
public class Emulator {

    private static final String ADB_DIR = System.getenv("ANDROID_HOME") + "\\platform-tools\\";
    public static final String ADB = System.getenv("ANDROID_HOME") + "\\platform-tools\\adb ";

    private final ExecutionParameters execParams;

    private final Runtime commandLine;
    @Getter
    private final Random random;

    private String emulatorInfo;
    private Long emulatorPort;
    private Writer telnet;

    private final int DEVICE_X = 500;
    private final int DEVICE_Y = 500;

    public Emulator(ExecutionParameters execParams) {
        this.execParams = execParams;
        commandLine = Runtime.getRuntime();
        checkEmulatorRunning();
        extractEmulatorPort();
        Long seed = execParams.getSeed().orElse(ThreadLocalRandom.current().nextLong());
        log.info("Usando semilla: " + seed);
        random = new Random(seed);
    }

    private void extractEmulatorPort() {
        emulatorPort = Long.valueOf(emulatorInfo.replaceAll("[^0-9]", ""));
        log.info("Puerto Emulador: " + emulatorPort);
    }

    public void checkEmulatorRunning() {
        final String command = ADB + " devices";
        Process proc = null;
        try {
            proc = commandLine.exec(command);
        } catch (IOException e) {
            ExceptionUtil.failCommandExec(command);
        }
        Scanner s = new Scanner(proc.getInputStream()).useDelimiter("\n");
        s.next();//Discard header
        if (s.hasNext()) {
            emulatorInfo = s.next();
            if (!emulatorInfo.trim().endsWith("device")) {
                fail();
            }
        } else {
            fail();
        }
        log.info("Emulator encontrado [OK]");
    }

    private void fail() {
        throw new IllegalStateException("No se puede encontrar ningun emulador corriendo");
    }

    public void initTelnet() {
        String telnetToken = execParams.getTelnetToken().orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get() {
                return new IllegalArgumentException("Se requiere el parametro -tt indicando el token de telnet");
            }
        });

        if (telnet == null) {
            String command = "telnet localhost " + emulatorPort;
            try {
                Process telnetPrc = commandLine.exec(command);
                telnet = new BufferedWriter(new OutputStreamWriter(telnetPrc.getOutputStream()));
                telnet.write("auth " + telnetToken + "\n");
                telnet.flush();
            } catch (IOException e) {
                ExceptionUtil.failCommandExec(command);
            }
        }
    }

    public void sendTelnetCommand(String command) {
        initTelnet();
        try {
            telnet.write(command + "\n");
            telnet.flush();
        } catch (IOException e) {
            ExceptionUtil.failCommandExec(command);
        }
    }

    public void sendAdbCommand(String command) {
        String adbCmd = ADB + " " + command;
        try {
            commandLine.exec(adbCmd);
        } catch (IOException e) {
            ExceptionUtil.failCommandExec(adbCmd);
        }
    }

    public int getX() {
        return DEVICE_X;
    }

    public int getY() {
        return DEVICE_Y;
    }
}