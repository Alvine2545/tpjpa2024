package fr.istic.taa.jaxrs.rest;

import com.stripe.exception.ApiException;
import fr.istic.taa.jaxrs.DTO.UserDto;
import fr.istic.taa.jaxrs.dao.generic.DAO.UserDao;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.DTO.LoginRequestDto;
import  fr.istic.taa.jaxrs.DTO.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces({"application/json", "application/xml"})
public class UserResource {
    private UserDao userDao = new UserDao();
    @GET
    @Path("/{userId}")
    @Operation(summary = "Get user by id",
            tags = {"users"},
            responses = {
                    @ApiResponse(description = "The user",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
                    @ApiResponse(responseCode = "400", description = "User not found") })

    public Response getUserById( @Parameter(description = "The id that needs to be fetched. Use 1 for testing. ", required = true) @PathParam("userId") Long userId) throws ApiException {

        User user = userDao.findOne(userId);
        if (user == null) {
            //throw new io.swagger.sample.exception.NotFoundException(404, "User not found");
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        return Response.ok(new UserDto(user)).build();
    }

    @POST
    @Consumes("application/json")
    @Operation(summary = "Create user",
            tags = {"users"},
            description = "This can only be done by the logged in user.")
    public Response addUser(@Parameter(description = "Created user object", required = true) User user) {
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
    @Path("/login-user")
    public Response loginUser(LoginRequestDto loginDto) {
        System.out.println(loginDto);
        return login(loginDto, "USER");
    }

    @POST
    @Path("/login-admin")
    public Response loginAdmin(LoginRequestDto loginDto) {
        return login(loginDto, "ADMIN");
    }

    @POST
    @Path("/login-org")
    public Response loginOrganisateur(LoginRequestDto loginDto) {
        return login(loginDto, "ORGANISATEUR");
    }

    /*@POST
    @Path("/login")

    /*public Response login(LoginRequestDto loginDto) {

        User user = userDao.findByEmail(loginDto.getUsername());

        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            return Response.ok(new LoginResponseDto("user-" + user.getId())).build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }*/

    private Response login(LoginRequestDto loginDto, String requiredRole) {
        User user = userDao.findByEmail(loginDto.getUsername());

        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            boolean hasRole = user.getRoles().stream()
                    .anyMatch(role -> role.getName().equalsIgnoreCase(requiredRole));

            if (hasRole) {
                return Response.ok(new LoginResponseDto(requiredRole.toLowerCase() + "-" + user.getId())).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN) // Rôle insuffisant
                        .entity("Access denied: role '" + requiredRole + "' required.").build();
            }
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
