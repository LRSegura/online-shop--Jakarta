package com.lab.onlineshop.webservice.customer;

import com.lab.onlineshop.api.exceptions.EntityFieldValueException;
import com.lab.onlineshop.model.customer.*;
import com.lab.onlineshop.model.webservices.SimpleResponse;
import com.lab.onlineshop.ui.customer.CustomerEvents;
import com.lab.onlineshop.webservice.util.ResponseStatus;
import jakarta.ejb.EJB;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/")
public class WsCustomer {

    @EJB
    private CustomerEvents customerEvents;

    @GET
    @Path("application/customers")
    public Response getCustomers(){
        Jsonb jsonb = JsonbBuilder.create();
        List<JsonCustomers> jsonUsersList = customerEvents.getCustomerService().getCustomers().stream().map(customer ->
                new JsonCustomers(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getUserName(), customer.getPassword(),
                        customer.getEmail(), customer.getCustomerLevel().getDescription(), customer.getAddress(), customer.getPhoneNumber(),
                        customer.getRegisterDate(), false)).toList();
        return Response.status(Response.Status.OK).entity(jsonb.toJson(jsonUsersList)).build();
    }


    @POST
    @Path("application/customers/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveCustomer(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonAddCustomer jsonAddCustomer = jsonb.fromJson(json, JsonAddCustomer.class);
        SimpleResponse response;
        try {
            Customer newCustomer = getCustomerFromJson(jsonAddCustomer);
            customerEvents.saveWithValidation(newCustomer);
            response = new SimpleResponse(ResponseStatus.SUCCESS.getStatus(),"");
        } catch (EntityFieldValueException exception) {
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),exception.getMessage(),exception.getSpecificErrorList());
        } catch (Exception exception){
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),"Error saving user: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private Customer getCustomerFromJson(JsonAddCustomer jsonAddCustomer){
        Customer customer = new Customer();
        return getCustomer(customer, jsonAddCustomer.firstName(), jsonAddCustomer.lastName(), jsonAddCustomer.userName(), jsonAddCustomer.password(),
                jsonAddCustomer.email(), jsonAddCustomer.customerLevel(), jsonAddCustomer.address(), jsonAddCustomer.phoneNumber());
    }

    @DELETE
    @Path("application/customers/delete")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteCustomer(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonDeleteCustomer jsonDeleteCustomer = jsonb.fromJson(json, JsonDeleteCustomer.class);
        List<Customer> customers = new ArrayList<>();
        jsonDeleteCustomer.customersId().forEach(id -> customers.add(customerEvents.getEntity(Customer.class, id)));
        customerEvents.delete(customers);
        SimpleResponse response = new SimpleResponse(true,"");
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    @PUT
    @Path("application/customers/update")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response updateCustomer(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonUpdateCustomer jsonUpdateCustomer = jsonb.fromJson(json, JsonUpdateCustomer.class);
        SimpleResponse response;
        try {
            Customer updateCustomer = getCustomerFromJson(jsonUpdateCustomer);
            customerEvents.updateWithValidation(updateCustomer);
            response = new SimpleResponse(ResponseStatus.SUCCESS.getStatus(),"");
        } catch (EntityFieldValueException exception) {
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(), exception.getMessage(), exception.getSpecificErrorList());
        }
        catch (Exception exception){
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),"Error updating Customer: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private Customer getCustomerFromJson(JsonUpdateCustomer jsonUpdateCustomer){
        Customer customer = customerEvents.getEntity(Customer.class, jsonUpdateCustomer.id());
        return getCustomer(customer, jsonUpdateCustomer.firstName(), jsonUpdateCustomer.lastName(), jsonUpdateCustomer.userName(), jsonUpdateCustomer.password(),
                jsonUpdateCustomer.email(), jsonUpdateCustomer.customerLevel(), jsonUpdateCustomer.address(), jsonUpdateCustomer.phoneNumber());
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
}
