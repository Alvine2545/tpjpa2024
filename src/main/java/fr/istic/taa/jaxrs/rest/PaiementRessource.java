package fr.istic.taa.jaxrs.rest;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import fr.istic.taa.jaxrs.DTO.TicketPaymentRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/payment")
public class PaiementRessource {

    @POST
    @Path("/create-checkout-session")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCheckoutSession(TicketPaymentRequest request) {

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:4200/paiement/success?session_id={CHECKOUT_SESSION_ID}")
                        .setCancelUrl("http://localhost:4200/paiement/cancel")
                        .addLineItem(

                                SessionCreateParams.LineItem.builder()

                                        .setQuantity((long) request.getQuantity())
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("eur")
                                                        .setUnitAmount((long) (request.getAmount() * 100))
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                        .setName(request.getLabel())
                                                                        .build())
                                                        .build())
                                        .build())
                        .build();

        try {
            Session session = Session.create(params);
            Map<String, String> res = new HashMap<>();
            res.put("url", session.getUrl());
            return Response.ok(res).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}