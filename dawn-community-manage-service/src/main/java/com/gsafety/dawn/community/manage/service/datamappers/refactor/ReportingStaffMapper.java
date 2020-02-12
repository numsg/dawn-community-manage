package com.gsafety.dawn.community.manage.service.datamappers.refactor;

import com.gsafety.dawn.community.manage.contract.model.refactor.ReportingStaffStatistics;
import com.gsafety.dawn.community.manage.service.entity.refactor.PlotReportingStaffEntity;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportingStaffMapper {

    @Mappings({
            @Mapping(target = "plotName", ignore = true)
    })
    ReportingStaffStatistics entityToModel(PlotReportingStaffEntity entity);

    List<ReportingStaffStatistics> entitiesToModels(List<PlotReportingStaffEntity> entities);

}
