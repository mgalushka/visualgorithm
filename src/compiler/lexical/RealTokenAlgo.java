/*
 * RealTokenAlgo.java v0.10 28/08/08
 *
 * Visualgorithm
 * Copyright (C) Hannier, Pironin, Rigoni (visualgo@googlegroups.com)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package compiler.lexical;

/**
 * A token which represents a real.
 *
 * @author Damien Rigoni
 * @version 0.10 28/08/08
 */
public class RealTokenAlgo extends TokenAlgo {
   
    final private double real;
    
    public RealTokenAlgo(String r) {
        super(TokenAlgoType.REAL, r);
        
        real = Double.valueOf(r);
    }

    public double getReal() {
        return real;
    }
}
