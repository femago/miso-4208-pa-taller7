/*
 * @(#)DevicesTest.java
 *
 * Copyright (c) 2017 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Southwest Airlines, Co.
 */
package co.edu.uniandes.miso4208.test;

import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Scanner;

@Log
public class DevicesTest {
    @Test
    public void test() throws IOException {
        String adb_dir = System.getenv("ANDROID_HOME") + "/platform-tools/";
        log.info(adb_dir);
        Process exec = Runtime.getRuntime().exec(adb_dir + "adb devices");
        InputStream is = exec.getInputStream();
        Scanner s = new java.util.Scanner(is).useDelimiter("\n");
        log.info(s.next());
        String next = s.next();
        log.info(next);
        Assert.assertTrue(next.trim().endsWith("device"));
    }

}
