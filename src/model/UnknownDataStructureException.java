/*
 * UnknownDataStructureException.java v0.10 02/07/08
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

package model;

/**
 * Definition of the unknown data structure exception. This exception is
 * principally used in the io package and more precisely in the classes of the
 * loading of a data structure from a file.
 * 
 * @author Damien Rigoni
 * @version 0.10 02/07/08
 */
public class UnknownDataStructureException extends Exception {

    private static final long serialVersionUID = 1L;

    private String description;

    /**
     * Builds the unknown data structure exception with the message passed in
     * parameter.
     * 
     * @param desc the message corresponding to the exception
     */
    public UnknownDataStructureException(String desc) {
        description = desc;
    }

    @Override
    public String getMessage() {
        return description;
    }
}
