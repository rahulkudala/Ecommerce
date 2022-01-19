package com.example.Project.AccountService.Services;

import com.example.Project.AccountService.Entities.CustomerAddressEntity;
import com.example.Project.AccountService.Entities.RegisterEntity;
import com.example.Project.AccountService.Models.CustomerAddressModel;
import com.example.Project.AccountService.Models.RegisterModel;
import com.example.Project.AccountService.Repositories.CustomerAddressRepository;
import com.example.Project.AccountService.Repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegisterService {

    @Autowired
    RegisterRepository registerRepository;
    @Autowired
    CustomerAddressRepository customerAddressRepository;

    private List<RegisterEntity> regList = new ArrayList<RegisterEntity>();
    private List<CustomerAddressModel> addList = new ArrayList<CustomerAddressModel>();

    public String addCustomer(RegisterModel registerModel){

        List<CustomerAddressEntity> addresslu = new ArrayList<>();

        if(registerModel == null)
        {
            return "Enter data in Body";

        }
        else if(registerRepository.findByEmail(registerModel.getEmail()) != null){
            return "Email Already exist";
        }
        else {
                JasyptService jasyptService = new JasyptService();
                RegisterEntity registerEntity = new RegisterEntity();

                registerEntity.setFirstName(registerModel.getFirstName());
                registerEntity.setLastName(registerModel.getLastName());
                registerEntity.setEmail(registerModel.getEmail());
                registerEntity.setPhoneNo(registerModel.getPhoneNo());
                registerEntity.setPassword(jasyptService.encrypt(registerModel.getPassword()));

                // adding address
               /* registerModel.getCustomerAddress().forEach(a -> {
                    CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();

                    customerAddressEntity.setLine1(a.getLine1());
                    customerAddressEntity.setLine2(a.getLine2());
                    customerAddressEntity.setCity(a.getCity());
                    customerAddressEntity.setState(a.getState());
                    customerAddressEntity.setPostalCode(a.getPostalCode());
                    customerAddressEntity.setBillingAddress(a.isBillingAddress());
                    customerAddressEntity.setShippingAddress(a.isShippingAddress());
                    customerAddressEntity.setRegisterEntity(registerEntity);

                    addresslu.add(customerAddressEntity);

                    customerAddressRepository.save(customerAddressEntity);

                });*/

                registerEntity.setCustomerAddressEntities(addresslu);

                regList.add(registerEntity);
                registerRepository.save(registerEntity);

                return "Customer Added";
        }
    }

    public List getAll() {

        List<RegisterEntity> li = registerRepository.findAll();
        return li.stream().map( x -> getRegistered(x)).collect(Collectors.toList());

    }

    public String addCustomerAddress(CustomerAddressModel customerAddressModel, String email) {

        RegisterEntity registerEntity = registerRepository.findByEmail(email);

        List<CustomerAddressEntity> address = registerEntity.getCustomerAddressEntities();

//        if(l1.isPresent())
//        {
//            RegisterEntity registerEntity = new RegisterEntity();
//            l1.stream().forEach(x -> {

              CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();

                customerAddressEntity.setLine1(customerAddressModel.getLine1());
                customerAddressEntity.setLine2(customerAddressModel.getLine2());
                customerAddressEntity.setPostalCode(customerAddressModel.getPostalCode());
                customerAddressEntity.setState(customerAddressModel.getState());
                customerAddressEntity.setCity(customerAddressModel.getCity());
                customerAddressEntity.setShippingAddress(customerAddressModel.isShippingAddress());
                customerAddressEntity.setBillingAddress(customerAddressModel.isBillingAddress());
                customerAddressEntity.setRegisterEntity(registerEntity);

                address.add(customerAddressEntity);

//                x.setCustomerAddressEntities(Arrays.asList(customerAddressEntity));
                registerEntity.setCustomerAddressEntities(address);

            //    registerRepository
                //   .save(registerEntity);

                customerAddressRepository.save(customerAddressEntity);
                registerRepository.save(registerEntity);
//            });


            /*List<CustomerAddressEntity> l2 = new ArrayList<>();
            l2.add(customerAddressEntity);

            RegisterEntity registerEntity = new RegisterEntity();
            registerEntity.setCustomerAddressEntities(l2);

            customerAddressRepository.save(customerAddressEntity);
            registerRepository
           .save(registerEntity);*/

            return "Address is Added";

        }
//        else
//            return "Customer is not present";
//
//    }

    public RegisterModel getCustAdress(Integer id)
    {
        Optional<RegisterEntity> l1 = registerRepository.findById(id);
        if(l1.isPresent()){

            return (RegisterModel) l1.stream().map(x -> getCustDetails(x)).collect(Collectors.toList());
        }
        else
            return null;
    }

    private RegisterModel getCustDetails(RegisterEntity registerEntity) {

        return new RegisterModel(
                registerEntity.getId(),
                registerEntity.getFirstName(),
                registerEntity.getLastName(),
                registerEntity.getEmail(),
                registerEntity.getPassword(),
                registerEntity.getPhoneNo());
    }

    private RegisterModel getRegistered(RegisterEntity registerEntity){

        List<CustomerAddressModel> address = new ArrayList<>();

        registerEntity.getCustomerAddressEntities().stream().forEach(x -> {
            CustomerAddressModel cm = new CustomerAddressModel();
            cm.setLine1(x.getLine1());
            cm.setLine2(cm.getLine2());
            cm.setState(x.getState());
            cm.setCity(x.getCity());
            cm.setPostalCode(x.getPostalCode());
            cm.setShippingAddress(x.isShippingAddress());
            cm.setBillingAddress(x.isBillingAddress());

            address.add(cm);


        });

        return new RegisterModel(
                registerEntity.getId(),
                registerEntity.getFirstName(),
                registerEntity.getLastName(),
                registerEntity.getEmail(),
                registerEntity.getPassword(),
                registerEntity.getPhoneNo(),
                address
        );
    }

}