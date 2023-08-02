package com.university.studentservice.service;

import com.university.studentservice.entity.AddressDto;
import com.university.studentservice.openFeign.AddressClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommonService {

    @Autowired
    private AddressClient addressClient;

    long count=1;


    @CircuitBreaker(name="addressService",fallbackMethod = "fallbackOfGetAddressResponse")
    public AddressDto getAddressResponse(long id){
        log.info("inside address response");
        log.info("count is : "+ count);
        count++;
//        Mono<AddressDto> response =webClient.get().uri("/"+id).retrieve().bodyToMono(AddressDto.class);
//        return response.block();
        AddressDto response=addressClient.getStudent(id);
        return response;
    }

    public AddressDto fallbackOfGetAddressResponse(long id,Throwable th){
        log.info("Error : "+ th);
        return new AddressDto();
    }
}
