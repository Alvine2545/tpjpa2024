package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.DTO.ArtisteDto;
import fr.istic.taa.jaxrs.DTO.ConcertDto;
import fr.istic.taa.jaxrs.dao.generic.DAO.ArtisteDAO;
import fr.istic.taa.jaxrs.domain.Artiste;
import fr.istic.taa.jaxrs.domain.Concert;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/artistes")
@Produces({"application/json", "application/xml"})
public class ArtisteRessource {
    private ArtisteDAO artisteDAO = new ArtisteDAO();

    @GET
    @Path("/")
    public Response getAllArtistes() {
        // On récupère tous les rôles depuis le DAO
        List<Artiste> artistes = artisteDAO.findAll();
        if (artistes == null || artistes.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(artistes).build();
    }


    @GET
    @Path("/{artisteId}")
    public Response getArtisteById(@PathParam("artisteId") Long artisteId)  {
        Artiste artiste = artisteDAO.findOne(artisteId);
        if (artiste == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Concert not found").build();
        }
        return Response.ok(new ArtisteDto(artiste)).build();
    }


    @POST
    @Consumes("application/json")
    public Response addArtiste(@Parameter(description = "User object that needs to be added to the store", required = true) Artiste artiste) {
        System.out.println("Requête POST reçue : " + artiste);

        artisteDAO.save(artiste);
        return Response.ok().entity("SUCCESS").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteArtiste(@PathParam("id") Long id) {
        Artiste artiste = artisteDAO.findOne(id);
        if (artiste == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Artiste not found").build();
        }
        artisteDAO.delete(artiste);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateArtiste(@PathParam("id") Long id, Artiste updatedArtiste) {
        Artiste existingArtiste = artisteDAO.findOne(id);
        if (existingArtiste == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingArtiste.setNom(updatedArtiste.getNom());
        existingArtiste.setPrenom(updatedArtiste.getPrenom());
        existingArtiste.setStyle(updatedArtiste.getStyle());
        existingArtiste.setConcerts(updatedArtiste.getConcerts()); // Attention à bien gérer ça
        artisteDAO.update(existingArtiste);

        return Response.ok(new ArtisteDto(existingArtiste)).build();
    }
}
