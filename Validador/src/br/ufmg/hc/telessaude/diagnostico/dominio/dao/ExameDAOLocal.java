/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.dominio.dao;

import br.ufmg.hc.telessaude.diagnostico.dominio.daoImpl.ExameDAOImpl;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Exame;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Paciente;
import br.ufmg.hc.telessaude.diagnostico.dominio.entity.Status;
import br.ufmg.hc.telessaude.diagnostico.dominio.exceptions.DAOException;
import br.ufmg.hc.telessaude.diagnostico.dominio.hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author paulo.gomes
 */
public class ExameDAOLocal extends ExameDAOImpl {

    public List<Exame> consultar(Integer id, String nomePaciente, Date inicio, Date fim) throws DAOException {
        ArrayList<Criterion> restrict = new ArrayList();
        if (id != null) {
            restrict.add(Restrictions.eq("id", id));
        } else {
            if (nomePaciente != null && !nomePaciente.isEmpty()) {
                restrict.add(Restrictions.ilike("pc.nome", nomePaciente, MatchMode.ANYWHERE));
            }
            if (inicio != null) {
                restrict.add(Restrictions.ge("datainclusao", inicio));
            }
            if (fim != null) {
                restrict.add(Restrictions.le("datainclusao", fim));
            }
        }
        try {
            final DetachedCriteria crit = DetachedCriteria.forClass(c);
            final Criteria criteria = crit.getExecutableCriteria(HibernateUtil.currentSession());

            crit.createAlias("paciente", "pc");
            crit.createAlias("status", "st");

            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("id"))
                    .add(Projections.property("pc.nome"))
                    .add(Projections.property("pc.datanascimento"))
                    .add(Projections.property("datainclusao"))
                    .add(Projections.property("st.nome")));

            criteria.addOrder(Order.desc("datainclusao"));

            for (final Criterion cri : restrict) {
                criteria.add(cri);
            }

            final List<Object[]> arrays = criteria.list();
            final List<Exame> exames = new ArrayList<>();

            for (Object[] array : arrays) {
                final Exame exame = new Exame(Integer.parseInt(String.valueOf(array[0])));
                exame.setPaciente(new Paciente());
                exame.getPaciente().setNome(String.valueOf(array[1]));
                exame.getPaciente().setDatanascimento((Date) array[2]);
                exame.setDatainclusao((Date) array[3]);
                exame.setStatus(new Status());
                exame.getStatus().setNome(String.valueOf(array[4]));

                exames.add(exame);
            }

            return exames;

        } catch (HibernateException ex) {
            throw new DAOException(ex.getMessage());
        }

    }

}
