package com.lab.onlineshop.webservice.customer;

import com.lab.onlineshop.model.customer.*;
import com.lab.onlineshop.webservice.EntityWebService;
import com.lab.onlineshop.webservice.customer.json.model.JsonAddCustomer;
import com.lab.onlineshop.webservice.customer.json.model.JsonCustomers;
import com.lab.onlineshop.webservice.customer.json.model.JsonDeleteCustomer;
import com.lab.onlineshop.webservice.customer.json.model.JsonUpdateCustomer;
import com.lab.onlineshop.webservice.json.JsonAdd;
import com.lab.onlineshop.webservice.json.JsonUpdate;
import com.lab.onlineshop.webservice.json.response.JsonDataResponse;
import com.lab.onlineshop.ui.customer.CustomerEvents;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.function.Supplier;

@Path("/")
public class WsCustomer implements EntityWebService<Customer, CustomerEvents> {

    @EJB
    private CustomerEvents customerEvents;

    @GET
    @Path("application/customers")
    public Response getCustomers(){
        Supplier<List<? extends JsonDataResponse>> supplier = () -> customerEvents.getCustomerService().getCustomers().stream().map(customer ->
                new JsonCustomers(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getUserName(), customer.getPassword(),
                        customer.getEmail(), customer.getCustomerLevel().getDescription(), customer.getAddress(), customer.getPhoneNumber(),
                        customer.getRegisterDate(), false)).toList();
        return get(supplier);
    }

    @POST
    @Path("application/customers/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveCustomer(String json){
        return save(customerEvents,json, JsonAddCustomer.class);
    }

    @DELETE
    @Path("application/customers/delete")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteCustomer(String json){
        return delete(customerEvents,Customer.class, JsonDeleteCustomer.class,json);
    }

    @PUT
    @Path("application/customers/update")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response updateCustomer(String json){
        return update(customerEvents,JsonUpdateCustomer.class,json);
    }
    private Customer getCustomer(Customer customer, String firstName, String lastName, String userName, String password, String email,
                                 String customerLevel, String address, String phoneNumber) {
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setUserName(userName);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setCustomerLevel(CustomerLevel.getCustomerLevel(customerLevel));
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        return customer;
    }

    @Override
    public Customer getEntityFromJson(JsonAdd json) {
        Customer customer = new Customer();
        JsonAddCustomer jsonAddCustomer = (JsonAddCustomer) json;
        return getCustomer(customer, jsonAddCustomer.firstName(), jsonAddCustomer.lastName(), jsonAddCustomer.userName(), jsonAddCustomer.password(),
                jsonAddCustomer.email(), jsonAddCustomer.customerLevel(), jsonAddCustomer.address(), jsonAddCustomer.phoneNumber());
    }

    @Override
    public Customer getEntityFromJson(JsonUpdate json) {
        JsonUpdateCustomer jsonUpdateCustomer = (JsonUpdateCustomer) json;
        Customer customer = customerEvents.getEntity(Customer.class, jsonUpdateCustomer.id());
        return getCustomer(customer, jsonUpdateCustomer.firstName(), jsonUpdateCustomer.lastName(), jsonUpdateCustomer.userName(), jsonUpdateCustomer.password(),
                jsonUpdateCustomer.email(), jsonUpdateCustomer.customerLevel(), jsonUpdateCustomer.address(), jsonUpdateCustomer.phoneNumber());
    }
}
