package com.IMSTask;

import org.apache.log4j.Logger;

/**
 * Created by yegorm on 06.09.2016.
 */
public class IMSLogger {

    private final static Logger LOGGER = Logger.getLogger(PlayerService.class);

    public static Logger getLogger(){
        return LOGGER;
    }

}
