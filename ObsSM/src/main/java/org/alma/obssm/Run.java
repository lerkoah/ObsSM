/*******************************************************************************
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
 * 
 * @autor Javier Fuentes j.fuentes.m(at)icloud.com
 * @version 0.1
 * 
 *******************************************************************************/

package org.alma.obssm;


import org.alma.obssm.sm.StateMachine;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.alma.obssm.net.ServerLineReader;
import org.alma.obssm.parser.Parser;
import org.apache.commons.scxml.model.ModelException;
import org.xml.sax.SAXException;



public class Run {
    public static void main(String args[]) {
        Run run = new Run();
    }
    
    public Run()
    {
        try {
            ServerLineReader slr = new ServerLineReader(8888);
            System.out.println("Server on the line!");
            StateMachine sm = new StateMachine("/Users/javier/Desktop/ObsSM.xml");
            System.out.println("SCXML parsed");
            Parser p = new Parser("/Users/javier/Desktop/ObsSM.json");
            System.out.println("JSON transitions subjects parsed");
            
            System.out.println("Loop started and waiting for logs on port: " + slr.getServerSocket().getLocalPort());
            while(true)
            {
                String line = slr.waitForLine();
                sm.fireEvent(p.getParseAction(line, sm.getTransitionsStringList()));
                if (line.equals("EOF")) 
                {
                    slr.killserver();
                    break;
                }
            }
            
            System.out.println("Loop ended");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModelException | SAXException ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}