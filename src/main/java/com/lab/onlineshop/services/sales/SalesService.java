package com.lab.onlineshop.services.sales;

import com.lab.onlineshop.model.Service;

public sealed interface SalesService extends Service permits SalesServiceImplementation{
}
