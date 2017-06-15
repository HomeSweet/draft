package com.IMSTask;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by yegorm on 07.09.2016.
 */
public class RestApiApplication extends ResourceConfig {

    public RestApiApplication (){
        register(PlayerService.class);
    }
}
