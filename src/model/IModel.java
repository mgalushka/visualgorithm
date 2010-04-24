/*
 * IModel.java v0.10 06/12/09
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

import java.util.EventListener;

/**
 * This interface is the common interface of all models. It is used by the
 * controllers of the software. This interface must be implemented by all models
 * of the software directly or through a more specific interface. It is possible
 * to add a model listener to the model in order to listen the modifications of
 * the model.
 *
 * @author Julien Hannier
 * @version 0.10 06/12/09
 */
public interface IModel {
    
    /**
     * Adds a model listener to the model. If {@code listener} is not corresponding
     * to the type of model then an IllegalArgumentException is thrown.
     *
     * @param listener the listener to add
     */
    public void addModelListener(EventListener listener) throws IllegalArgumentException;

    /**
     * Removes a model listener from the model. If {@code listener} is not corresponding
     * to the type of model then an IllegalArgumentException is thrown.
     *
     * @param listener the listener to remove
     */
    public void removeModelListener(EventListener listener) throws IllegalArgumentException;
}
