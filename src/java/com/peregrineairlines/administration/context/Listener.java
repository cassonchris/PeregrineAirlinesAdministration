/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peregrineairlines.administration.context;

import com.peregrineairlines.model.PAModel;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Chris
 */
public class Listener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        PAModel.open();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        PAModel.close();
    }
    
}
