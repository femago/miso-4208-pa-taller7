/*
 * @(#)ExceptionUtil.java
 *
 * Copyright (c) 2017 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Southwest Airlines, Co.
 */
package co.edu.uniandes.miso4208.util;

public class ExceptionUtil {
    public static final void failCommandExec(String command) {
        throw new IllegalAccessError("El comando no pudo se ejecutado " + command);
    }
}
