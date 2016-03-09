package com.flat.controller;

import com.flat.model.Apartment;
import com.flat.utils.ApiCall;
import com.flat.utils.CollectionUtils;
import com.flat.utils.Notificator;
import com.flat.utils.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	Store store;

	@Autowired
	Notificator notificator;

	@RequestMapping("/refresh")
	@ResponseStatus(value = HttpStatus.OK)
	public void refresh() {
		try {
			List<Apartment> apartments = apiCall.doCall();
			List<Long> existingApartmentIds = store.getLastApartmentsIds();

			List<Apartment> newApartments = CollectionUtils.getNewApartments(apartments, existingApartmentIds);
			if (!newApartments.isEmpty()) {
				store.save(newApartments);
				notificator.notify(newApartments);
			}
		} catch (Throwable e) {
			notificator.notifyError(e);
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
}
