package com.sky.controller.admin;


import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@RestController
@RequestMapping("admin/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/turnoverStatistics")
    @ApiOperation("Turn Over Statistics")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {

        log.info("sales data: {},{}",begin,end);
        return Result.success(reportService.getTurnoverStatistic(begin, end));
    }


    @GetMapping("/userStatistics")
    @ApiOperation("Get user statistic")
    public Result<UserReportVO> userReportStatistic(   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("user report data: {},{}",begin,end);


        return Result.success(reportService.getUserReportStatistic(begin, end));
    }

    @GetMapping("/ordersStatistics")
    @ApiOperation("Get order statistic")
    public Result<OrderReportVO> orderReportStatistic(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("order report data: {},{}",begin,end);

        OrderReportVO orderStatisticsVO = reportService.getOrderReportStatistic(begin, end);

        return Result.success(orderStatisticsVO);
    }


    @GetMapping("/top10")
    @ApiOperation("Get top 10 sales statistic")
    public Result<SalesTop10ReportVO> salesReportStatistic(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("top 10 sales report data: {},{}",begin,end);
        SalesTop10ReportVO salesTop10ReportVO = reportService.getSalesReportStatistic(begin, end);

        return Result.success(salesTop10ReportVO);
    }


    /**
     * Export Business Data
     * @param response
     */
    @GetMapping("/export")
    @ApiOperation("导出运营数据报表")
    public void export(HttpServletResponse response){
        reportService.exportBusinessData(response);
    }


}
