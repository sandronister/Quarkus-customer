package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.dto.CustomerDTO;
import org.acme.services.CustomerService;

import java.util.List;

@Path("/api/v1/customer")
public class CustomerController {

    @Inject
    private CustomerService customerService;


    @GET()
    public List<CustomerDTO> findAllCustomer() {
        return customerService.findAll();
    }

    @GET()
    @Path("{id}")
    public CustomerDTO findCustomerById(@PathParam("id") Long id) {
        return customerService.findById(id);
    }

    @POST
    @Transactional
    public Response saveCustomer(CustomerDTO customerDTO) {
        try{
            customerService.saveCustomer(customerDTO);
            return Response.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        try{
            customerService.deleteCustomer(id);
            return Response.accepted().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


    @PUT
    @Transactional
    @Path("{id}")
    public Response updateCustomer(@PathParam("id")Long id, CustomerDTO customerDTO) {
        try{
            customerService.updateCustomer(id,customerDTO);
            return Response.accepted().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
