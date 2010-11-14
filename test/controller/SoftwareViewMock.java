/*
 * SoftwareViewMock.java v0.10 04/06/09
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

import view.IDataStructureView;
import view.ISoftwareView;

/**
 * Mock of the software view for the tests of controllers.
 * 
 * @author Julien Hannier
 * @version 0.10 04/06/09
 * @see ISoftwareView
 */
public class SoftwareViewMock implements ISoftwareView {

    public SoftwareViewMock(ISoftwareController c) {
    }

    @Override
    public void displayView() {
    }

    @Override
    public void closeView() {
    }

	@Override
	public void addDataStructureView(String name, IDataStructureView view) {
	}

	@Override
	public void deleteDataStructureView(int index) {
	}
}
