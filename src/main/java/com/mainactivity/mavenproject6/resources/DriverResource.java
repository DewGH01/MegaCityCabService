/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mainactivity.mavenproject6.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Dewmi
 */
@Path("driver")
public class DriverResource {
    private DBUtil dbUtil = new DBUtil(); // DB utility class to handle database operations

    // Create Driver
    @POST
    @Path("/createDriver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDriver(Driver driver) {
        try {
            dbUtil.createDriver(driver);  // Call DBUtil to create a new driver
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Driver created successfully!\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in creating driver.\"}")
                    .build();
        }
    }

    // Get All Drivers
    @GET
    @Path("/getAllDrivers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDrivers() {
        try {
            List<Driver> drivers = dbUtil.getAllDrivers();  // Call DBUtil to get all drivers
            if (!drivers.isEmpty()) {
                return Response.status(Response.Status.OK)
                        .entity(drivers)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"No drivers found!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in fetching drivers.\"}")
                    .build();
        }
    }

    // Get Driver by ID
    @GET
    @Path("/getDriver/{driverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDriverById(@PathParam("driverId") int driverId) {
        try {
            Driver driver = dbUtil.getDriverById(driverId);  // Call DBUtil to get driver by ID
            if (driver != null) {
                return Response.status(Response.Status.OK)
                        .entity(driver)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"Driver not found!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in fetching driver.\"}")
                    .build();
        }
    }

    // Update Driver
    @PUT
    @Path("/updateDriver")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDriver(Driver driver) {
        try {
            dbUtil.updateDriver(driver);  // Call DBUtil to update driver details
            return Response.status(Response.Status.OK)
                    .entity("{\"message\":\"Driver updated successfully!\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in updating driver.\"}")
                    .build();
        }
    }

    // Delete Driver
    @DELETE
    @Path("/deleteDriver/{driverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDriver(@PathParam("driverId") int driverId) {
        try {
            dbUtil.deleteDriver(driverId);  // Call DBUtil to delete driver by ID
            return Response.status(Response.Status.OK)
                    .entity("{\"message\":\"Driver deleted successfully!\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in deleting driver.\"}")
                    .build();
        }
    }

    // Check Driver Availability
    @GET
    @Path("/checkAvailability/{driverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkDriverAvailability(@PathParam("driverId") int driverId) {
        try {
            boolean isAvailable = dbUtil.checkDriverAvailability(driverId);  // Call DBUtil to check availability
            if (isAvailable) {
                return Response.status(Response.Status.OK)
                        .entity("{\"message\":\"Driver is available!\"}")
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"Driver is not available!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in checking driver availability.\"}")
                    .build();
        }
    }
}
