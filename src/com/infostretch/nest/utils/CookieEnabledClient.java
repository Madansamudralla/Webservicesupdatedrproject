package com.infostretch.nest.utils;

import com.qmetry.qaf.automation.ws.rest.DefaultRestClient;
import com.sun.jersey.api.client.Client;

public class CookieEnabledClient extends DefaultRestClient {

	@Override
	protected Client createClient() {
		Client client = super.createClient();
		client.addFilter(new CookiesFilter());
		return client;
	}
}
