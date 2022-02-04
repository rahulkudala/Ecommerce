package com.example.Project.AccountService.Services;

import com.example.Project.AccountService.Entities.CustomerAddressEntity;
import com.example.Project.AccountService.Entities.RegisterEntity;
import com.example.Project.AccountService.Models.CustomerAddressModel;
import com.example.Project.AccountService.Models.RegisterModel;
import com.example.Project.AccountService.Repositories.CustomerAddressRepository;
import com.example.Project.AccountService.Repositories.LoginRepository;
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

    @Autowired
    LoginRepository loginRepository;

//    private List<RegisterEntity> regList = new ArrayList<RegisterEntity>();
//    private List<CustomerAddressModel> addList = new ArrayList<CustomerAddressModel>();


    // Adding customer details
    public String addCustomer(RegisterModel registerModel){

        List<CustomerAddressEntity> addresslu = new ArrayList<>();

        if(registerModel == null)
        {
            return "Enter Customer Info";

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
                registerEntity.setCartEntityList(null);

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

            //    regList.add(registerEntity);
                registerRepository.save(registerEntity);

                return "Customer Added";
        }
    }

    // retrieving all details of Customers & there addresses
    public List getAll() {

        List<RegisterEntity> li = registerRepository.findAll();
        return li.stream().map( x -> getRegistered(x)).collect(Collectors.toList());

    }

    // Adding addresses to the Customer via email
    public String addCustomerAddress(CustomerAddressModel customerAddressModel, String email, String password) {

        RegisterEntity registerEntity = registerRepository.findByEmail(email);

        JasyptService jasyptService = new JasyptService();
        if(registerEntity != null &&
                registerEntity.getEmail().equals(email) &&
                jasyptService.decrypt(registerEntity.getPassword()).equals(password)) {

            List<CustomerAddressEntity> address = registerEntity.getCustomerAddressEntities();

            if (customerAddressModel == null) {
                return "Address is null";
            } else if (registerRepository.findByEmail(email) == null) {
                return "Customer is not present";
            } else if (registerEntity.getEmail().equals(email)) {
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

                registerEntity.setCustomerAddressEntities(address);

                //         customerAddressRepository.save(customerAddressEntity);
                registerRepository.save(registerEntity);
//            });

                return "Address is Added";
            }
            else
                return null;
        }
        else
            return "User is not Registered! / Credentials are Wrong";
//
    }

    public List getCustAddress(Integer id)
    {

//        List<CustomerAddressEntity> addressEntities = customerAddressRepository.findAll();
        Optional<RegisterEntity> registerEntities = registerRepository.findById(id);

        if(registerEntities.isPresent()){

            List<CustomerAddressModel> customerAddressModels = new ArrayList<>();
            registerEntities.get().getCustomerAddressEntities().stream().forEach(x -> {

                CustomerAddressModel customerAddressModel = new CustomerAddressModel();
                customerAddressModel.setLine1(x.getLine1());
                customerAddressModel.setLine2(x.getLine2());
                customerAddressModel.setState(x.getState());
                customerAddressModel.setCity(x.getCity());
                customerAddressModel.setPostalCode(x.getPostalCode());
                customerAddressModel.setBillingAddress(x.isBillingAddress());
                customerAddressModel.setShippingAddress(x.isShippingAddress());

                customerAddressModels.add(customerAddressModel);
            });

            return customerAddressModels;
        }
        else
            return null;
    }


    public List getCustAddress(String email)
    {

//        List<CustomerAddressEntity> addressEntities = customerAddressRepository.findAll();
        Optional<RegisterEntity> registerEntities = Optional.ofNullable(registerRepository.findByEmail(email));

        if(registerEntities.isPresent()){

            List<CustomerAddressModel> customerAddressModels = new ArrayList<>();
            registerEntities.get().getCustomerAddressEntities().stream().forEach(x -> {

                CustomerAddressModel customerAddressModel = new CustomerAddressModel();
                customerAddressModel.setLine1(x.getLine1());
                customerAddressModel.setLine2(x.getLine2());
                customerAddressModel.setState(x.getState());
                customerAddressModel.setCity(x.getCity());
                customerAddressModel.setPostalCode(x.getPostalCode());
                customerAddressModel.setBillingAddress(x.isBillingAddress());
                customerAddressModel.setShippingAddress(x.isShippingAddress());

                customerAddressModels.add(customerAddressModel);
            });

            return customerAddressModels;
        }
        else
            return null;
    }

    private CustomerAddressModel getAddress(CustomerAddressEntity addressEntity) {

        Optional<CustomerAddressEntity> customerAddressEntity = customerAddressRepository.findById(addressEntity.getId());
        List<CustomerAddressModel> address = new ArrayList<>();

        address.add(new CustomerAddressModel(
                customerAddressEntity.get().getLine1(),
                customerAddressEntity.get().getLine2(),
                customerAddressEntity.get().getPostalCode(),
                customerAddressEntity.get().getState(),
                customerAddressEntity.get().getCity(),
                customerAddressEntity.get().isShippingAddress(),
                customerAddressEntity.get().isBillingAddress()
        ));

        return null;
    }

    private RegisterModel getRegistered(RegisterEntity registerEntity){

        List<CustomerAddressModel> address = new ArrayList<>();

        registerEntity.getCustomerAddressEntities().stream().forEach(x -> {
            CustomerAddressModel cm = new CustomerAddressModel();
            cm.setLine1(x.getLine1());
            cm.setLine2(x.getLine2());
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

    public String login(String email, String password) {

        RegisterEntity registerEntity = registerRepository.findByEmail(email);

        if(registerEntity != null) {

            JasyptService jasyptService = new JasyptService();
            Authentication authentication = new Authentication();

            if (registerEntity.getEmail().equals(email) &&
                    jasyptService.decrypt(registerEntity.getPassword()).equals(password)) {

                String token = authentication.generateToken(email);

                return "Login Successful\n" + token;

            }
            else
                return "Authentication Failed!";
        }
        else
            return "Email not exit";
    }
}