/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.ant.internal.ui.model;

import org.apache.tools.ant.Target;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


public class InternalTargetFilter extends ViewerFilter {
	
	private int fFiltered= 0;
	/**
	 * Returns whether the given target is an internal target. Internal
	 * targets are targets which have no description. The default target
	 * is never considered internal.
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		boolean result= true;
		if (element instanceof AntTargetNode) {
			Target target= ((AntTargetNode)element).getTarget();
			result= target.getDescription() != null || ((AntTargetNode)element).isDefaultTarget();
		} 
		if (!result) {
			fFiltered++;
		}
		return result;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#filter(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object[])
	 */
	public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
		fFiltered= 0;
		return super.filter(viewer, parent, elements);
	}
	
	public int getNumberOfTargetsFiltered() {
		return fFiltered;
	}
}