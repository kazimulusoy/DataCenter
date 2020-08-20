package com.datacenter;

public class WebApiConstant {
	/**
     * The REST API version for all the endpoints from this project.
     */
    public static final String REST_VERSION = "v1";

    /**
     * The prefix for all the URL for from this project.
     */
    public static final String BASE_URL = "/api/" + REST_VERSION;

    /**
     * The prefix for all the URL for the WoS REST.
     */
    public static final String DATA_CENTER_URL = BASE_URL + "/datacenters";


    /**
     * Do not let anyone to instantiate this class.
     */
    private WebApiConstant() {
        // UNIMPLEMENTED
    }
}
