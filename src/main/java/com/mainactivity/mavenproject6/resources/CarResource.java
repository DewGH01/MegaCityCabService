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
@Path("car") // The path for this resource
public class CarResource {
    private DBUtil dbUtil = new DBUtil();  // Assuming DBUtil handles the database operations

    // Get All Cars (Admin only)
    @GET
    @Path("/getAllCars")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCars() {
        try {
            List<Car> cars = dbUtil.getAllCars();
            if (!cars.isEmpty()) {
                return Response.status(Response.Status.OK)
                        .entity(cars)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"No cars found!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in fetching all cars.\"}")
                    .build();
        }
    }

    // Get Car by ID
    @GET
    @Path("/getCar/{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarById(@PathParam("carId") int carId) {
        try {
            Car car = dbUtil.getCarById(carId);
            if (car != null) {
                return Response.status(Response.Status.OK)
                        .entity(car)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"Car not found!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in fetching car by ID.\"}")
                    .build();
        }
    }

    // Create Car (Admin only)
    @POST
    @Path("/createCar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCar(Car car) {
        try {
            dbUtil.createCar(car);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Car created successfully!\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in creating car.\"}")
                    .build();
        }
    }

    // Update Car (Admin only)
    @PUT
    @Path("/updateCar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCar(Car car) {
        try {
            dbUtil.updateCar(car);
            return Response.status(Response.Status.OK)
                    .entity("{\"message\":\"Car updated successfully!\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in updating car.\"}")
                    .build();
        }
    }

    // Delete Car (Admin only)
    @DELETE
    @Path("/deleteCar/{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCar(@PathParam("carId") int carId) {
        try {
            dbUtil.deleteCar(carId);
            return Response.status(Response.Status.OK)
                    .entity("{\"message\":\"Car deleted successfully!\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in deleting car.\"}")
                    .build();
        }
    }

    // Check Car Availability
    @GET
    @Path("/checkAvailability/{carId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCarAvailability(@PathParam("carId") int carId) {
        try {
            boolean isAvailable = dbUtil.checkCarAvailability(carId);
            if (isAvailable) {
                return Response.status(Response.Status.OK)
                        .entity("{\"message\":\"Car is available!\"}")
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"Car is not available!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in checking car availability.\"}")
                    .build();
        }
    }
}
