package com.prapp.examplesecurityjwtbasic.mapper;

import com.prapp.examplesecurityjwtbasic.dao.entity.CompanyEntity;
import com.prapp.examplesecurityjwtbasic.expose.dto.CompanyDto;
import com.prapp.examplesecurityjwtbasic.expose.dto.PagedResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")  // Cambio para que MapStruct use Spring como framework de inyección de dependencias
public interface CompanyMapper {

    PagedResult<CompanyDto.Response> toResponsePage(PagedResult<CompanyEntity> entity);

    List<CompanyDto.Response> toResponseList(List<CompanyEntity> entity);

    CompanyEntity toRequest(CompanyDto.Request request);

    CompanyDto.Response toResponse(CompanyEntity entity);
}
