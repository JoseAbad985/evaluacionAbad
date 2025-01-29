package org.example.Services;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.DAOs.CandidatoDAO;
import org.example.Models.Candidatos;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

import java.sql.*;

@Path("/Candidato")
public class CandidatoService {

    @Inject
    private CandidatoDAO candidatoDAO;

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCandidatos() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        String query = "SELECT lista, nombre, edad, ciudad, u_titulo_reg, actividad, Partido, Binomio FROM Candidatos";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                arrayBuilder.add(
                        Json.createObjectBuilder()
                                .add("lista" , rs.getString("lista"))
                                .add("nombre", rs.getString("nombre"))
                                .add("edad", rs.getInt("edad"))
                                .add("ciudad", rs.getString("ciudad"))
                                .add("u_titulo_reg", rs.getString("u_titulo_reg"))
                                .add("actividad", rs.getString("actividad"))
                                .add("Partido", rs.getString("Partido"))
                                .add("Binomio", rs.getString("Binomio"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Database error: " + e.getMessage()).build();
        }

        JsonArray candidatosArray = arrayBuilder.build();
        return Response.ok(candidatosArray).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCandidato(Candidatos candidato) {
        String query = "INSERT INTO Candidatos (lista, nombre, edad, ciudad, u_titulo_reg, actividad, Partido, Binomio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, candidato.getLista());
            stmt.setString(2, candidato.getNombre());
            stmt.setString(3, candidato.getEdad());
            stmt.setString(4, candidato.getCiudad());
            stmt.setString(5, candidato.getU_titulo_reg());
            stmt.setString(6, candidato.getActividad());
            stmt.setString(7, candidato.getPartido());
            stmt.setString(8, candidato.getBinomio());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                return Response.status(Response.Status.CREATED)
                        .entity(Json.createObjectBuilder().add("message", "Candidato agregado exitosamente").build())
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Json.createObjectBuilder().add("message", "Error al insertar candidato").build())
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Json.createObjectBuilder().add("message", "Database error: " + e.getMessage()).build())
                    .build();
        }
    }
}

