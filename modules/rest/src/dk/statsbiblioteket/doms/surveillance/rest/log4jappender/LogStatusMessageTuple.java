/* $Id$
 * $Revision$
 * $Date$
 * $Author$
 *
 * The DOMS project.
 * Copyright (C) 2007-2009  The State and University Library
*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
 */
package dk.statsbiblioteket.doms.surveillance.rest.log4jappender;

import dk.statsbiblioteket.doms.surveillance.rest.StatusMessageTuple;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Date;

/** A status message tuple initialised with a log event. */
public class LogStatusMessageTuple extends StatusMessageTuple {
    /**
     * Helper method to map logging level to severity.
     * Severity maps to YELLOW for warnings, RED for fatal and error.
     * All else GREEN.
     *
     * @param level A log level.
     * @return The severity.
     */
    private static Severity getSeverity(Level level) {
        switch (level.toInt()) {
            case Level.FATAL_INT:
            case Level.ERROR_INT:
                return Severity.RED;
            case Level.WARN_INT:
                return Severity.YELLOW;
            default:
                return Severity.GREEN;
        }
    }

    /**
     * Initialise the status message by converting the relevant fields from the
     * log event.
     * Severity maps to YELLOW for warnings, RED for fatal and error.
     * All else GREEN.
     *
     * @param event The log event.
     */
    public LogStatusMessageTuple(LoggingEvent event) {
        super(
                event.getRenderedMessage(), getSeverity(event.getLevel()),
                new Date(event.getTimeStamp()), true);
    }


}