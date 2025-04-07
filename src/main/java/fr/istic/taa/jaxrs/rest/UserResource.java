package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.DTO.UserDto;
import fr.istic.taa.jaxrs.dao.generic.DAO.UserDao;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.DTO.LoginRequestDto;
import  fr.istic.taa.jaxrs.DTO.LoginResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces({"application/json", "application/xml"})
public class UserResource {
    private UserDao userDao = new UserDao();
    @GET
    @Path("/{userId}")
    public Response getUserById(@PathParam("userId") Long userId)  {
        User user = userDao.findOne(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        return Response.ok(new UserDto(user)).build();
    }

    @POST
    @Consumes("application/json")
    public Response addUser(@Parameter(description = "User object that needs to be added to the store", required = true) User user) {
        //User user = new User(userDTO.getNom(), userDTO.getEmail());
        System.out.println("Requête POST reçue : " + user);
        userDao.save(user);
        return Response.ok().entity("SUCCESS").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User user = userDao.findOne(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        userDao.delete(user);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    /*@PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, UserDto userDTO) {
        User existingUser = userDao.findOne(id);
        if (existingUser == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Utilisateur non trouvé").build();
        }
        existingUser.setNom(userDTO.getNom());
        existingUser.setEmail(userDTO.getEmail());
        userDao.update(existingUser);
        return Response.ok(new UserDto(existingUser)).build();
    }*/


    @POST
    @Path("/login")
    public Response login(LoginRequestDto loginDto) {
        User user = userDao.findByEmail(loginDto.getUsername());

        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            return Response.ok(new LoginResponseDto("user-" + user.getId())).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
