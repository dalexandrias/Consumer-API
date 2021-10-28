package com.application.repository;

import com.application.dto.ReportDTO;
import com.application.model.Shopping;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository {

    public List<Shopping> getReportByFilters(Date dataInicio, Date dataFim, Float valorMinimo);

    public ReportDTO getReportByDate(Date dataInicio, Date dataFim);
}
