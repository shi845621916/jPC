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
import Hardware.CPU.Intel80386.Register.Segments.Descriptor;



public final class VERW extends Instruction {

    private final Operand m_selector;
    
    public VERW(Intel80386 cpu,
                Operand selector) {
        
        super(cpu);
        
        m_selector = selector;
    }

    @Override
    public void run() {
        
        if(m_cpu.CR.isInRealMode() || m_cpu.FLAGS.VM)
            throw CPUException.getInvalidOpcode();
        
        
        int selector = m_selector.getValue();
        
        if(m_cpu.isNullSelector(selector)) {

            m_cpu.FLAGS.ZF = false;
        }
        else {

            // Read descriptor
            Descriptor desc;
            if((desc = m_cpu.getDescriptor(selector)) == null) {

                m_cpu.FLAGS.ZF = false;
                return;
            }

            // The type has to be a writable data segment
            if(!desc.getTypeInfo().isWritableDataSegment()) {

                m_cpu.FLAGS.ZF = false;
                return;
            }

            // Check access rights
            if(desc.getDPL() < m_cpu.getCPL() ||
               desc.getDPL() < m_cpu.getSelectorsRPL(selector)) {

                m_cpu.FLAGS.ZF = false;
                return;
            }

            m_cpu.FLAGS.ZF = true;
        }
    }
    
    @Override
    public String toString() {
        
        return String.format("verw %s", m_selector.toString());
    }
}
