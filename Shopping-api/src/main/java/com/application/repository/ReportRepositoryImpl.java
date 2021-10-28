package com.application.repository;

import com.application.dto.ReportDTO;
import com.application.model.Shopping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Shopping> getReportByFilters(Date dataInicio, Date dataFim, Float valorMinimo) {
        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("select s from shop s where s.date >= :dataInicio ");

        if (dataFim != null) {
            stringQuery.append("and s.date <= :dataFim ");
        }

        if (valorMinimo != null) {
            stringQuery.append("and s.total <= :valorMinimo");
        }

        Query query = entityManager.createQuery(stringQuery.toString());
        query.setParameter("dataInicio", dataInicio);

        if (dataFim != null) {
            query.setParameter("dataFim", dataFim);
        }

        if (valorMinimo != null) {
            query.setParameter("valorMinimo", valorMinimo);
        }

        return (List<Shopping>) query.getResultList();
    }

    @Override
    public ReportDTO getReportByDate(Date dataInicio, Date dataFim) {

        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("select count(sp.id), sum(sp.total), avg(sp.total) ");
        stringQuery.append("from shopping.shop sp where sp.date >= :dataInicio ");
        stringQuery.append("and sp.date <= :dataFim");

        Query query = entityManager.createNativeQuery(stringQuery.toString());
        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);

        Object[] result = (Object[]) query.getSingleResult();
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setCount(((BigInteger) result[0]).intValue());
        reportDTO.setTotal((Double) result[1]);
        reportDTO.setMean((Double) result[2]);

        return reportDTO;
    }
}
