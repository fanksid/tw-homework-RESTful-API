package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    private static final String COMPANY_URL_BASE = "/companies";
    //在此处完成Company API

    @Autowired
    private CompanyRepository companyRepository;

    /**
     * Get company list
     *
     * @return company list
     */
    @GetMapping(value = COMPANY_URL_BASE)
    public List<Company> list() {
        return companyRepository.findAll();
    }

    /**
     * Get a company by id
     *
     * @param id
     * @return company
     */
    @GetMapping(value = COMPANY_URL_BASE + "/{id}")
    public Company get(@PathVariable Long id) {
        return companyRepository.findOne(id);
    }

    /**
     * Add a company
     *
     * @param company new company
     * @return added company
     */
    @PostMapping(value = COMPANY_URL_BASE)
    public Company add(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    /**
     * Update company
     *
     * @param id company id
     * @param newCompany new company data
     * @return updated company
     */
    @PutMapping(value = COMPANY_URL_BASE + "/{id}")
    public Company update(@PathVariable Long id, @RequestBody Company newCompany) {
        newCompany.setId(id);
        return companyRepository.save(newCompany);
    }

    /**
     * Delete company
     *
     * @param id company id
     * @return null
     */
    @DeleteMapping(value = COMPANY_URL_BASE + "/{id}")
    public String delete(@PathVariable Long id) {
        companyRepository.delete(id);
        // TODO: 删除该company下所有的employee
        return "";
    }
}
