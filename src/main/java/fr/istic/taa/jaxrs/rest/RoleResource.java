package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.DTO.RoleDto;
import fr.istic.taa.jaxrs.DTO.UserDto;
import fr.istic.taa.jaxrs.dao.generic.DAO.RoleDao;
import fr.istic.taa.jaxrs.domain.Role;
import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/roles")
@Produces({"application/json", "application/xml"})
public class RoleResource {
    RoleDao roleDao = new RoleDao();
    @POST
    @Consumes("application/json")
    public Response addRole(@Parameter(description = "Role object that needs to be added to the store", required = true)Role role) {
        System.out.println("Requête POST reçue : " + role);
        roleDao.save(role);
        return Response.ok().entity("SUCCESS").build();
    }
    @GET
    @Path("/{roleId}")
    public Response getRole(@PathParam("roleId") Long roleId)  {
        Role role = roleDao.findOne(roleId);
        if (role == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found").build();
        }
        return Response.ok(new RoleDto(role)).build();
    }

    @GET
    @Path("/")
    public Response getRoles() {
        // On récupère tous les rôles depuis le DAO
        List<Role> roles = roleDao.findAll();
        if (roles == null || roles.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(roles).build();
    }

}
