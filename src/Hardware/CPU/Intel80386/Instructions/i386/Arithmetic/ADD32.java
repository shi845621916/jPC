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
package Hardware.CPU.Intel80386.Instructions.i386.Arithmetic;

import Hardware.CPU.Intel80386.Instructions.Instruction;
import Hardware.CPU.Intel80386.Intel80386;
import Hardware.CPU.Intel80386.Operands.Operand;



public final class ADD32 extends Instruction {

    private final Operand m_destination;
    private final Operand m_source;
    
    public ADD32(Intel80386 cpu,
                 Operand destination,
                 Operand source) {
        
        super(cpu);
        
        m_destination = destination;
        m_source = source;
    }
    
    @Override
    public void run() {
        
        int dest = m_destination.getValue();
        int src = m_source.getValue();
        int result = dest + src;
        
        m_cpu.FLAGS.setSZP32(result);
        m_cpu.FLAGS.CF = Integer.compareUnsigned(result, dest) < 0;
        m_cpu.FLAGS.OF = (((dest ^ src ^ 0x80000000) & (result ^ src)) & 0x80000000) != 0;
        m_cpu.FLAGS.AF = (((dest ^ src) ^ result) & 0x10) != 0;
        
        m_destination.setValue(result);
    }
    
    @Override
    public String toString() {
        
        return String.format("add %s, %s", m_destination.toString(), m_source.toString());
    }
}
