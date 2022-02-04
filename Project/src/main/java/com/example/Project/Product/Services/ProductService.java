package com.example.Project.Product.Services;

import com.example.Project.Product.Models.PriceModel;
import com.example.Project.Product.Models.SkuModel;
import com.example.Project.Product.Entities.PriceEntity;
import com.example.Project.Product.Entities.ProductEntity;
import com.example.Project.Product.Entities.SkuEntity;
import com.example.Project.Product.Models.ProductModel;
import com.example.Project.Product.Repository.PriceRepository;
import com.example.Project.Product.Repository.ProductRepository;
import com.example.Project.Product.Repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SkuRepository skuRepository;

    @Autowired
    PriceRepository priceRepository;

    // Adding Product
    public String addProducts(ProductModel productModel) {

        ProductEntity productEntity = new ProductEntity();

        List<SkuEntity> skuEntityList = new ArrayList<>();

        productEntity.setProductCode(productModel.getProductCode());
        productEntity.setProductName(productModel.getProductName());
        productEntity.setDescription(productModel.getDescription());

        productEntity.setSkuEntityList(skuEntityList);

        productRepository.save(productEntity);

        return "Product is Added";
    }

    //Adding Sku to Product using Product-Code
    public String addSku(SkuModel skuModel){

        Optional<ProductEntity> productsEntity = Optional.ofNullable(productRepository.findByProductCode(skuModel.getProductCode()));

        if(productsEntity.isPresent()){

            SkuEntity skuEntity = new SkuEntity();

            skuEntity.setSkuCode(skuModel.getSkuCode());
            skuEntity.setProductCode(skuModel.getProductCode());
            skuEntity.setSize(skuModel.getSize());
            skuEntity.setProductEntity(productsEntity.get());

            productsEntity.get().getSkuEntityList().add(skuEntity);
            //skuRepository.save(skuEntity);
            productRepository.save(productsEntity.get());

            return "Sku is added";
        }

        else
            return "Product Not present !";
    }

    // Adding Price to the Sku
    public String addPrice(PriceModel priceModel){

        SkuEntity skuEntity = skuRepository.findBySkuCode(priceModel.getSkuCode());
        Optional<ProductEntity> productsEntity = productRepository.findById(priceModel.getSkuCode());
        if(skuEntity!=null) {

            PriceEntity priceEntity = new PriceEntity();

            priceEntity.setSkuCode(priceModel.getSkuCode());
            priceEntity.setPrice(priceModel.getPrice());
            priceEntity.setCurrency(priceModel.getCurrency());

            skuEntity.setPriceEntity(priceEntity);

            skuRepository.save(skuEntity);
            priceRepository.save(priceEntity);

            return "Price is added";

        }
        else
            return "Sku is not exist";
    }

    public List getAllProducts(){

        /*List<ProductEntity> productEntity = productRepository.findAll();
        List<PriceEntity> priceEntities = priceRepository.findAll();

        List all = new ArrayList();

        all.add(productEntity.stream().map(x -> getProducts(x)).collect(Collectors.toList()));
        all.add(priceEntities.stream().map(x -> getPrices(x)).collect(Collectors.toList()));

        return all;*/
        return productRepository.findAll();
    }

    // Product Entity to Model conversion
    private ProductModel getProducts(ProductEntity productEntity){

        List<SkuModel> skus = new ArrayList<>();
        List<Integer> id = new ArrayList<>();

        productEntity.getSkuEntityList().stream().forEach(x -> {
            id.add(x.getSkuCode());
            SkuModel skuModel = new SkuModel();

            skuModel.setProductCode(x.getProductCode());
            skuModel.setSkuCode(x.getSkuCode());
            skuModel.setSize(x.getSize());
//                skuModel.setPriceModel(x.getPriceEntity().add());
            skus.add(skuModel);

        });

        return new ProductModel(
                productEntity.getProductCode(),
                productEntity.getProductName(),
                productEntity.getDescription(),
                skus
        );
    }

    //Price Entity to Model Conversion
    public PriceModel getPrices(PriceEntity priceEntity){

        return new PriceModel(

                priceEntity.getSkuCode(),
                priceEntity.getPrice(),
                priceEntity.getCurrency()
        );
    }
}
