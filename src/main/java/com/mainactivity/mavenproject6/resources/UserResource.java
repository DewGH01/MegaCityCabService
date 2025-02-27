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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

 

/**
 *
 * @author Dewmi
 */
@Path("user")
public class UserResource {
      private DBUtil dbUtil = new DBUtil();  // Assuming DBUtil is the utility class for DB operations

    // Sign-Up
    @POST
    @Path("signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(User user) {
        try {
            // Check if user already exists
            boolean userExists = dbUtil.checkUserExists(user.getEmail());
            if (userExists) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"message\":\"User already exists with this email!\"}")
                        .build();
            }
            // Register user
            dbUtil.signUpUser(user);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"User created successfully!\"}")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in creating user.\"}")
                    .build();
        }
    }

    // Login
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        try {
            User loggedInUser = dbUtil.loginUser(user.getEmail(), user.getPassword());
            if (loggedInUser != null) {
                return Response.status(Response.Status.OK)
                        .entity("{\"message\":\"Login successful!\"}")
                        .build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"message\":\"Invalid email or password!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in login.\"}")
                    .build();
        }
    }

    // Get User (Logged in user)
    @GET
    @Path("getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@QueryParam("userId") int userId) {
        try {
            User user = dbUtil.getUser(userId);
            if (user != null) {
                return Response.status(Response.Status.OK)
                        .entity(user)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"User not found!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in fetching user.\"}")
                    .build();
        }
    }

    // Get All Users (Admin only)
    @GET
    @Path("getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        try {
            List<User> users = dbUtil.getAllUsers();
            if (!users.isEmpty()) {
                return Response.status(Response.Status.OK)
                        .entity(users)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"No users found!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in fetching all users.\"}")
                    .build();
        }
    }

    // Update User (Admin)
    @PUT
    @Path("updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        try {
            boolean updated = dbUtil.updateUser(user);
            if (updated) {
                return Response.status(Response.Status.OK)
                        .entity("{\"message\":\"User updated successfully!\"}")
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"User not found to update!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in updating user.\"}")
                    .build();
        }
    }

    // Delete User (Admin)
    @DELETE
    @Path("deleteUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@QueryParam("email") String email) {
        try {
            boolean deleted = dbUtil.deleteUser(email);
            if (deleted) {
                return Response.status(Response.Status.OK)
                        .entity("{\"message\":\"User deleted successfully!\"}")
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"message\":\"User not found to delete!\"}")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\":\"Error in deleting user.\"}")
                    .build();
        }
    }
}
