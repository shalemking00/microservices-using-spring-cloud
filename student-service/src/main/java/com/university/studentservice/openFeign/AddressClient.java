package com.university.studentservice.openFeign;

import com.university.studentservice.entity.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "api-gateway")
public interface AddressClient {

    @GetMapping("/address-service/api/v1/address/{id}")
    public AddressDto getStudent(@PathVariable long id);

}
