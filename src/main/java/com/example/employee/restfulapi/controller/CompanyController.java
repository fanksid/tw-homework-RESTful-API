package com.example.employee.restfulapi.controller;

import com.example.employee.restfulapi.entity.Company;
import com.example.employee.restfulapi.entity.Employee;
import com.example.employee.restfulapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping(value = COMPANY_URL_BASE + "/page/{page}/pageSize/{pageSize}")
    public List<Company> listPage(@PathVariable Integer page,
                                  @PathVariable Integer pageSize) {
        return companyRepository.findAll(new PageRequest(page, pageSize)).getContent();
    }

    @GetMapping(value = COMPANY_URL_BASE + "/{id}/employees")
    public List<Employee> listEmployee(@PathVariable Long id) {
        return companyRepository.findOne(id).getEmployees();
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
        Company company = companyRepository.findOne(id);
        company.setEmployeesNumber(newCompany.getEmployeesNumber());
        company.setCompanyName(newCompany.getCompanyName());
        return companyRepository.save(company);
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
        return "";
    }
}
