/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.dominio.dao;

import br.ufmg.hc.telessaude.diagnostico.dominio.daoImpl.LaudoDAOImpl;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Laudo;
import br.ufmg.hc.telessaude.diagnostico.dominio.exceptions.DAOException;
import br.ufmg.hc.telessaude.diagnostico.dominio.hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author paulo.gomes
 */
public class LaudoDAOLocal extends LaudoDAOImpl {

    @Override
    public List<Laudo> consultarPorIdExame(Integer id) throws DAOException {
        try {
            final DetachedCriteria crit = DetachedCriteria.forClass(c);
            final Criteria criteria = crit.getExecutableCriteria(HibernateUtil.currentSession());

            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("id"))
                    .add(Projections.property("datainicio"))
                    .add(Projections.property("conteudo")));

            criteria.add(Restrictions.eq("exame.id", id));

            final List<Object[]> arrays = criteria.list();
            final List<Laudo> laudos = new ArrayList();

            for (Object[] array : arrays) {
                final Laudo laudo = new Laudo(Integer.parseInt(String.valueOf(array[0])));
                laudo.setDatainicio((Date) array[1]);
                laudo.setConteudo(array[2].toString());
                laudos.add(laudo);
            }

            return laudos;

        } catch (HibernateException ex) {
            throw new DAOException(ex.getMessage());
        }

    }
}
