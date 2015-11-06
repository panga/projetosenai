package br.com.zanivan.projetosenai.dao;

import br.com.zanivan.projetosenai.model.Contato;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author leonardo
 */
@Stateless
public class ContatoDAO {

    @PersistenceContext(unitName = "contatosPU")
    private EntityManager em;

    public void insere(Contato contato) {
        em.persist(contato);
    }
    
    public void excluir(Long id) {
        em.remove(em.getReference(Contato.class, id));
    }
    
    public Contato buscar(Long id) {
        return em.find(Contato.class, id);
    }
    
    public Contato atualizar(Contato contato) {
        return em.merge(contato);
    }

    public List<Contato> lista() {
        TypedQuery<Contato> q = em.createQuery("SELECT c "
                + "FROM Contato c ORDER BY c.nome", Contato.class);
        return q.getResultList();
    }
}
