/*
 * IController.java v0.10 16/06/08
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

package controller;

import view.IView;

/**
 * This interface is the common interface of all controllers. It must be
 * implemented by all controllers of the software directly or through a more
 * specific interface.
 * 
 * @author Julien Hannier
 * @version 0.10 16/06/08
 */
public interface IController {

  /**
   * Returns the view managed by the controller.
   *
   * @return the view managed by controller
   */
  public IView getView();
}
