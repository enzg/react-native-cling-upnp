/**
 * Copyright (C) 2013 Aurélien Chabot <aurelien@chabot.fr>
 * 
 * This file is part of DroidUPNP.
 * 
 * DroidUPNP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DroidUPNP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DroidUPNP.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.droidupnp.model.upnp;

import org.droidupnp.Main;

public class ContentDirectoryDiscovery extends DeviceDiscovery {

	protected static final String TAG = "ContentDirectoryDeviceFragment";

	public ContentDirectoryDiscovery(IServiceListener serviceListener)
	{
		super(serviceListener);
	}

	@Override
	protected ICallableFilter getCallableFilter()
	{
		return new CallableContentDirectoryFilter();
	}

	@Override
	protected boolean isSelected(IUpnpDevice device)
	{
		if (Main.upnpServiceController != null && Main.upnpServiceController.getSelectedContentDirectory() != null)
			return device.equals(Main.upnpServiceController.getSelectedContentDirectory());

		return false;
	}

	@Override
	protected void select(IUpnpDevice device)
	{
		select(device, false);
	}

	@Override
	protected void select(IUpnpDevice device, boolean force)
	{
		Main.upnpServiceController.setSelectedContentDirectory(device, force);
	}

	@Override
	protected void removed(IUpnpDevice d)
	{
		if (Main.upnpServiceController != null && Main.upnpServiceController.getSelectedContentDirectory() != null
				&& d.equals(Main.upnpServiceController.getSelectedContentDirectory()))
			Main.upnpServiceController.setSelectedContentDirectory(null);
	}
}
