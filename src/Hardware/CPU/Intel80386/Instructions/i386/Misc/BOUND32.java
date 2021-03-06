/*
 * Copyright (C) 2017 h0MER247
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package Hardware.CPU.Intel80386.Instructions.i386.Misc;

import Hardware.CPU.Intel80386.Exceptions.CPUException;
import Hardware.CPU.Intel80386.Instructions.Instruction;
import Hardware.CPU.Intel80386.Intel80386;
import Hardware.CPU.Intel80386.Operands.Operand;



public final class BOUND32 extends Instruction {
    
    private final Operand m_index;
    private final Operand m_lowBoundary;
    private final Operand m_highBoundary;

    public BOUND32(Intel80386 cpu,
                   Operand index,
                   Operand lowBoundary,
                   Operand highBoundary) {
        
        super(cpu);
        
        m_index = index;
        m_lowBoundary = lowBoundary;
        m_highBoundary = highBoundary;
    }
    
    @Override
    public void run() {
        
        int index = m_index.getValue();
        
        if(index < m_lowBoundary.getValue() || index > m_highBoundary.getValue())
            throw CPUException.getBoundCheckFailed();
    }
}
