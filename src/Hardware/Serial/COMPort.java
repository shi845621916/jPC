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
package Hardware.Serial;



public interface COMPort {
    
    public interface COMPortDevice {
        
        void onDTRChanged(boolean dtr, boolean dtrOld);
        void onRTSChanged(boolean rts, boolean rtsOld);
        void onDataReceived(int data);
        void onUpdateDevice();
    }
    
    void sendData(int data);
    void setDCD(boolean dcd);
    void setDSR(boolean dsr);
    void setRI(boolean ri);
    void setCTS(boolean cts);
}
