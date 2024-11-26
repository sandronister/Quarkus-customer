package org.acme.services;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.CustomerDTO;
import org.acme.entity.CustomerEntity;
import org.acme.repository.CustomerRepository;


import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity customer = mapToEntity(customerDTO);
        customerRepository.persist(customer);
    }

    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerRepository.findAll().stream().forEach(item ->{
            customerDTOList.add(mapToDTO(item));
        });

        return customerDTOList;
    }

    public CustomerDTO findById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id);
        if (customerEntity == null) {
            return null;
        }
        return mapToDTO(customerEntity);
    }

    public void updateCustomer(Long id,CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerRepository.findById(id);
        customerEntity.setName(customerDTO.getName());
        customerEntity.setAge(customerDTO.getAge());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setAddress(customerDTO.getAddress());
        customerRepository.persist(customerEntity);
    }

    public void deleteCustomer(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id);
        customerRepository.delete(customerEntity);
    }

    private CustomerEntity mapToEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDTO.getName());
        customerEntity.setAge(customerDTO.getAge());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setAddress(customerDTO.getAddress());

        return customerEntity;
    }

    private CustomerDTO mapToDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customerEntity.getName());
        customerDTO.setAge(customerEntity.getAge());
        customerDTO.setPhone(customerEntity.getPhone());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setAddress(customerEntity.getAddress());
        return customerDTO;
    }
}
