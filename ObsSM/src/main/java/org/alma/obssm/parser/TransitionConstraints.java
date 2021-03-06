/**
 * *****************************************************************************
 * ALMA - Atacama Large Millimeter Array
 * Copyright (c) AUI - Associated Universities Inc., 2016
 * (in the framework of the ALMA collaboration).
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA
 ******************************************************************************
 */
package org.alma.obssm.parser;

import java.util.List;
import java.util.Objects;

/**
 * This class is used to describe a JSON element. It has 3 Lists and 3 String
 * and_list, or_list, search_list. and_list requires that all the elements on
 * the list must be on the log. or_list requires at least one element on the
 * list must be on the log. search_list is a list of regexpr that search for a
 * pattern on the log. keyname is the identifier of the SM
 *
 * @author Javier Fuentes Munoz j.fuentes.m@icloud.com
 * @version 0.3 
 *
 */
public class TransitionConstraints {

    /**
     * Event name or Transition.
     */
    public String eventName;

    /**
     * It allows to identifying the type of the transition:
     * initial, transition, final. Etc
     * 
     * It could be whatever you want: (Exception, Alert, Info, Flag, etc.).
     */
    public String eventType;

    /**
     *  AND List.
     *  It checks if a log line contains every element of this list.
     */
    public List<String> and_list;

    /**
     *  OR Lits.
     * It checks if at least one of the elements of this list are in the log line.
     */
    public List<String> or_list;

    /**
     * Regex list.
     * It checks if all the elements of this list does match with the log line
     */
    public List<String> search_list;

    /**
     * Log line ID. i.e. Array number.
     */
    public String keyName;

    @Override
    public String toString() {
        return "TransitionConstraints{" + "eventName=" + eventName + ", eventType=" + eventType + ", and_list=" + and_list + ", or_list=" + or_list + ", search_list=" + search_list + ", keyName=" + keyName + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.eventName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransitionConstraints other = (TransitionConstraints) obj;
        return Objects.equals(this.eventName, other.eventName);
    }

}
