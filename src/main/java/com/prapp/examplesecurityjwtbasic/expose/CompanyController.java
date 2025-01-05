package com.prapp.examplesecurityjwtbasic.expose;

import com.prapp.examplesecurityjwtbasic.business.CompanyService;
import com.prapp.examplesecurityjwtbasic.expose.dto.CompanyDto;
import com.prapp.examplesecurityjwtbasic.expose.dto.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyDto.Response>> list() {
        List<CompanyDto.Response> data = companyService.list();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/menu-page")
    public ResponseEntity<PagedResult<CompanyDto.Response>> listPage(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PagedResult<CompanyDto.Response> data = companyService.listPage(page, size);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto.Response> findById(@PathVariable UUID id) {
        CompanyDto.Response dto = companyService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CompanyDto.Response> save(@RequestBody CompanyDto.Request request) {
        CompanyDto.Response dto = companyService.save(request);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto.Response> update(
            @PathVariable UUID id,
            @RequestBody CompanyDto.Request request) {
        CompanyDto.Response dto = companyService.update(id, request);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
