package co.edu.uniandes.miso4208.config;

import co.edu.uniandes.miso4208.util.ExceptionUtil;
import lombok.Getter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.logging.Level;

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
    private PrintWriter telnet;

    private final int DEVICE_X = 1080;
    private final int DEVICE_Y = 1920;

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
        String telnetToken = execParams.getTelnetToken()
                .orElseThrow((Supplier<RuntimeException>) () -> new IllegalArgumentException("Se requiere el parametro -tt indicando el token de telnet"));

        if (telnet == null) {
            try {
                Socket socket = new Socket("localhost", emulatorPort.intValue());
                telnet = new PrintWriter(socket.getOutputStream(), true);
                telnet.println("auth " + telnetToken);
            } catch (IOException e) {
                log.severe("Error abriendo socket para comunicacion telnet");
                throw new RuntimeException(e);
            }
        }
    }

    public void sendTelnetCommand(String command) {
        initTelnet();
        telnet.println(command);
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