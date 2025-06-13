package com.sky.service;

import com.sky.vo.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public interface ReportService {

    /**
     * Calculate sales
     * @param begin
     * @param end
     * @return
     */
    TurnoverReportVO getTurnoverStatistic(LocalDate begin, LocalDate end);


    UserReportVO getUserReportStatistic(LocalDate begin, LocalDate end);

    OrderReportVO getOrderReportStatistic(LocalDate begin, LocalDate end);

    SalesTop10ReportVO getSalesReportStatistic(LocalDate begin, LocalDate end);

    void exportBusinessData(HttpServletResponse response);
}
