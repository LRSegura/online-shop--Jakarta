package com.lab.onlineshop.ui.home;

import com.lab.onlineshop.api.util.Context;
import com.lab.onlineshop.ui.EventsForms;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Named;

@ApplicationScoped
@Named(value = "homeEvents")
public class HomeEvents extends EventsForms {
    @EJB
    private Context applicationContext;

    public boolean renderMenuUser(){
        return applicationContext.isLoggedUser();
    }
    public boolean renderMenuCostumer(){
        return applicationContext.isLoggedCustomer();
    }
}
