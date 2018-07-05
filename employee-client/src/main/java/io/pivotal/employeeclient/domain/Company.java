package io.pivotal.employeeclient.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

    @JsonProperty("people")
    private List<Employee> employees;

    public List<Employee> getEmployees(){
        return employees;
    }
}
