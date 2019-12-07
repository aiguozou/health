package cn.skunk.service;

import java.util.Map;

public interface ReportService {
    Map<String, Object> getBusinessReportData() throws Exception;

    public Integer getThisMonthMember(Map map);
    Integer getThisMonthOrderMember(Map<String, String> map);
}
