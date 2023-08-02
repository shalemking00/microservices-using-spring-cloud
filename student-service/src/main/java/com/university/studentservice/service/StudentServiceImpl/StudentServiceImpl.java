package com.university.studentservice.service.StudentServiceImpl;

import com.university.studentservice.entity.AddressDto;
import com.university.studentservice.entity.Student;
import com.university.studentservice.entity.StudentDto;
import com.university.studentservice.entity.StudentResponse;
import com.university.studentservice.openFeign.AddressClient;
import com.university.studentservice.repository.StudentRepository;
import com.university.studentservice.service.CommonService;
import com.university.studentservice.service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressClient addressClient;

    @Autowired
    private CommonService commonService;

    @Autowired
    private WebClient webClient;

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student student=new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setAddressId(studentDto.getAddressId());
        return mapToDto(studentRepository.save(student));
    }

    @Override
    public StudentResponse getStudent(long id) {
        log.info("Inside the student service");
        Student student=studentRepository.findById(id).get();
        StudentResponse studResponse=new StudentResponse();
        studResponse.setFirstName(student.getFirstName());
        studResponse.setLastName(student.getLastName());
        studResponse.setEmail(student.getEmail());
        // using web client
//        studResponse.setAddressDto(getAddressResponse(student.getAddressId()));

        //using openfeign client
        studResponse.setAddressDto(commonService.getAddressResponse(student.getAddressId()));
        return studResponse;
    }

//    @CircuitBreaker(name="addressService",fallbackMethod = "fallbackOfGetAddressResponse")
//    public AddressDto getAddressResponse(long id){
//        log.info("inside address response");
////        Mono<AddressDto> response =webClient.get().uri("/"+id).retrieve().bodyToMono(AddressDto.class);
////        return response.block();
//        AddressDto response=addressClient.getStudent(id);
//        return response;
//    }
//
//    public AddressDto fallbackOfGetAddressResponse(long id,Throwable th){
//        return new AddressDto();
//    }




    public StudentDto mapToDto(Student student){
        StudentDto response=new StudentDto();
        response.setEmail(student.getEmail());
        response.setFirstName(student.getFirstName());
        response.setLastName(student.getLastName());
        response.setAddressId(student.getAddressId());
        return response;
    }
}
