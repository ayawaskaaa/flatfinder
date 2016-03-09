package com.flat.controller;

import com.flat.model.Apartment;
import com.flat.utils.ApiCall;
import com.flat.utils.Cache;
import com.flat.utils.Notificator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class BootController
{
	private static Logger LOGGER = Logger.getLogger(BootController.class.getName());

	@Autowired
	ApiCall apiCall;

	@Autowired
	Cache cache;

	@Autowired
	Notificator notificator;

	@RequestMapping("/refresh")
	@ResponseStatus(value = HttpStatus.OK)
	public void refresh() {
		try {
			List<Apartment> apartments = apiCall.doCall();
			if(!cache.isInitialized())
			{
				LOGGER.warning("Cache haven't been initialized, refresh it with new apartments");
				cache.setApartments(apartments);
				return;
			}
			List<Apartment> existingApartments = cache.getApartments();

			List<Apartment> newApartments = new ArrayList<>(apartments);
			newApartments.removeAll(existingApartments);

			if (!newApartments.isEmpty()) {
				cache.setApartments(apartments);
				notificator.notify(newApartments);
			}
		} catch (Throwable e) {
			notificator.notifyError(e);
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
}
