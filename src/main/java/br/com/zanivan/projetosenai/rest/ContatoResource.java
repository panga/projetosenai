package br.com.zanivan.projetosenai.rest;

import br.com.zanivan.projetosenai.dao.ContatoDAO;
import br.com.zanivan.projetosenai.model.Contato;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("contatos")
@Produces(MediaType.APPLICATION_JSON)
public class ContatoResource {

    @Inject
    private ContatoDAO contatoDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Contato insert(Contato contato) {
        contatoDAO.insere(contato);
        return contato;
    }

    @GET
    public List<Contato> list() {
        return contatoDAO.lista();
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        contatoDAO.excluir(id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Contato update(@PathParam("id") Long id,
            Contato contato) {
        if (!Objects.equals(id, contato.getId())) {
            throw new WebApplicationException
                            (Response.Status.BAD_REQUEST);
        }
        return contatoDAO.atualizar(contato);
    }
    
    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Long id) {
        final Contato contato = contatoDAO.buscar(id);
        if (contato == null) {
            throw new EntityNotFoundException();
        }
        return Response.ok(contato).build();
    }
}
