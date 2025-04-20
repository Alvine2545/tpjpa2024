package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.DTO.GenreDto;
import fr.istic.taa.jaxrs.DTO.RoleDto;
import fr.istic.taa.jaxrs.dao.generic.DAO.GenreDAO;
import fr.istic.taa.jaxrs.dao.generic.DAO.RoleDao;
import fr.istic.taa.jaxrs.domain.Genre;
import fr.istic.taa.jaxrs.domain.Role;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/genres")
@Produces({"application/json", "application/xml"})
public class GenreRessource {
    GenreDAO genreDAO = new GenreDAO();

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addGenre(@Parameter(description = "Role object that needs to be added to the store", required = true)Genre genre) {
        genreDAO.save(genre);
        return Response.ok().entity("SUCCESS").build();
    }
    @GET
    @Path("/{genreId}")
    public Response getGenre(@PathParam("genreId") Long genreId)  {
        Genre genre = genreDAO.findOne(genreId);
        if (genre == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Genre not found").build();
        }
        return Response.ok(new GenreDto(genre)).build();
    }

    @GET
    @Path("/")
    public Response getGenres() {
        List<Genre> genres = genreDAO.findAll();
        if (genres == null || genres.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        List<GenreDto> dtos = genres.stream()
                .map(GenreDto::new)
                .collect(Collectors.toList());

        return Response.ok(dtos).build();
    }
}
