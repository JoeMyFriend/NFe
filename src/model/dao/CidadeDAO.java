/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.bean.Cidade;
import model.bean.Estado;
import static nfe.NFe.emBs;

/**
 *
 * @author User
 */
public class CidadeDAO {

    public List<Cidade> findAll() {
        List<Cidade> cidades = null;
        try {
            cidades = emBs.createQuery("from Cidade c").getResultList();
        } catch (Exception e) {
            System.err.println(e);
        }
        return cidades;
    }

    public Cidade findByCodigoIbge(String codigoIbge) {
        Cidade cidade = null;
        try {
            Integer codigoCidade = Integer.valueOf(codigoIbge.substring(2));
            Integer codigoEstado = Integer.valueOf(codigoIbge.substring(0, 2));

            CriteriaBuilder cb = emBs.getCriteriaBuilder();

            CriteriaQuery<Cidade> cq = cb.createQuery(Cidade.class);

            //Metamodel m = em.getMetamodel();
            //EntityType<Cidade> Cidade_ = m.entity(Cidade.class);
            Root<Cidade> rootCidade = cq.from(Cidade.class);

            //Reference: http://www.objectdb.com/java/jpa/query/jpql/from
            Join<Cidade, Estado> rootEstado = rootCidade.join("estado", JoinType.LEFT);
            cq.multiselect(rootCidade, rootEstado);

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(rootCidade.get("codigoIbge"), codigoCidade));
            predicates.add(cb.equal(rootEstado.get("codigoIbge"), codigoEstado));

            cq.select(rootCidade).where(predicates.toArray(new Predicate[]{}));

            TypedQuery<Cidade> query = emBs.createQuery(cq);

            cidade = query.getSingleResult();
        } catch (Exception e) {
            System.err.println(e);
        }
        return cidade;
    }
}
