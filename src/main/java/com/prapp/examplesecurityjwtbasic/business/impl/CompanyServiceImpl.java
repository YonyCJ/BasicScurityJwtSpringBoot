package com.prapp.examplesecurityjwtbasic.business.impl;

import com.prapp.examplesecurityjwtbasic.business.CompanyService;
import com.prapp.examplesecurityjwtbasic.dao.entity.CompanyEntity;
import com.prapp.examplesecurityjwtbasic.dao.repository.CompanyRepository;
import com.prapp.examplesecurityjwtbasic.expose.dto.CompanyDto;
import com.prapp.examplesecurityjwtbasic.expose.dto.PagedResult;
import com.prapp.examplesecurityjwtbasic.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDto.Response> list() {
        List<CompanyEntity> entities = companyRepository.findAllByIsActiveTrue();
        return companyMapper.toResponseList(entities);
    }

    @Override
    public PagedResult<CompanyDto.Response> listPage(int page, int size) {
        List<CompanyEntity> menuEntities = companyRepository.findAllByIsActiveTrue()
                .stream()
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
        List<CompanyDto.Response> responses = companyMapper.toResponseList(menuEntities);
        // Usar el constructor con los 4 parámetros
        return new PagedResult<>(responses, companyRepository.count(), page, size);
    }



    @Override
    public CompanyDto.Response findById(UUID id) {
        CompanyDto.Response response = new CompanyDto.Response();
        Optional<CompanyEntity> getEntity = companyRepository.findByIdAndIsActiveTrue(id);
        if (getEntity.isPresent()) {
            response.setId(getEntity.get().getId());
            response.setName(getEntity.get().getName());
            response.setAddress(getEntity.get().getAddress());
            response.setEmail(getEntity.get().getEmail());
            response.setPhone(getEntity.get().getPhone());
            response.setRuc(getEntity.get().getRuc());
        }
        return response;
    }

    @Override
    @Transactional
    public CompanyDto.Response save(CompanyDto.Request request) {
        CompanyEntity entity = new CompanyEntity();
        entity.setName(request.getName());
        entity.setAddress(request.getAddress());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setRuc(request.getRuc());
        entity.setIsActive(true);
        entity.setCreatedAt(LocalDateTime.now());

        // Guardar la entidad
        companyRepository.save(entity);
        return companyMapper.toResponse(entity);
    }

    @Override
    @Transactional
    public CompanyDto.Response update(UUID id, CompanyDto.Request request) {
        Optional<CompanyEntity> getEntity = companyRepository.findByIdAndIsActiveTrue(id);
        if (!getEntity.isPresent()) {
            throw new IllegalArgumentException("Menu no encontrado: " + id);
        }

        CompanyEntity entity = new CompanyEntity();
        entity.setName(request.getName());
        entity.setAddress(request.getAddress());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        entity.setRuc(request.getRuc());
        entity.setUpdatedAt(LocalDateTime.now());

        companyRepository.save(entity);
        return companyMapper.toResponse(entity);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Optional<CompanyEntity> getEntity = companyRepository.findByIdAndIsActiveTrue(id);
        getEntity.ifPresent(entity -> {
            entity.setIsActive(false); // Cambia el campo isActive a false
            companyRepository.save(entity); // Guarda los cambios en la base de datos
        });
    }
}
