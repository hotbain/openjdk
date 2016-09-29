/*
 * Copyright (c) 2007, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.awt.*;
import java.awt.image.BufferedImage;


/*
 * @test
 * @summary Tests the add method of the SystemTray. Checks if it
 *          throws proper exceptions in case of invalid arguments and adds the
 *          TrayIcon correctly in case of a proper argument
 * @author Dmitriy Ermashov (dmitriy.ermashov@oracle.com)
 * @run main TrayIconAddTest
 */

public class TrayIconAddTest {

    public static void main(String[] args) throws Exception {
        if (! SystemTray.isSupported()) {
            System.out.println("SystemTray not supported on the platform under test. " +
                    "Marking the test passed");
        } else {
            new TrayIconAddTest().doTest();
        }
    }

    void doTest() throws Exception {
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(null);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException thrown correctly when add(null) called");
        }

        TrayIcon icon = new TrayIcon(new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB));

        tray.add(icon);

        try {
            tray.add(icon);
        } catch (IllegalArgumentException iae) {
            System.out.println("IllegalArgumentException rightly thrown when tray icon is added twice");
        }
    }
}
