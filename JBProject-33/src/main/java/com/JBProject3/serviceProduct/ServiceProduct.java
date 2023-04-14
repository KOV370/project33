package com.JBProject3.serviceProduct;

import com.JBProject3.modelProduct.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProduct {
    @Autowired
    ProductRepository productRepository;
    Product product = new Product();

    public ServiceProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ServiceProduct() {
    }

    private Product validateProduct(Product product) {
        String type = product.getType();
        product.setType(product.validateType(type));
        return product;
    }

    public Product create(Product product) {
        productRepository.save(validateProduct(product));
        return product;
    }

    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        products.forEach(System.out::println);
        return products;
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    public List<Product> findByName(String name) {
        Product product = new Product();
        product.setName(name); //установление имени, которое будет искаться - PROBE
        ExampleMatcher matcher = ExampleMatcher.matching()   //создание соответстивия для поиска
                .withMatcher("name", p -> p.contains()) //обозначение столбца поиска и установление параметра поиска
                //тут - содержит значение, др. варианты - начинается, заканчиваентя, рег. выражение
                .withIgnorePaths("id", "price", "type"); //игнорирование остальных параметров product
        return productRepository.findAll(Example.of(product, matcher)); //созд. шаблона на поиск: образец обьекта поиска и шаблон параметра поиска этого обьекта
    }

    public List<Product> findByType(String type) {
        Product product = new Product(0, null, 0.0, type);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("type", p -> p.ignoreCase().contains())
                .withIgnorePaths("id", "name", "price");
        return productRepository.findAll(Example.of(product, matcher));
    }

    public List<Product> findByPriceLessThan(double price) {
        return productRepository.findByPriceLessThan(price);
    }

    public boolean update(int id, String name, double price, String type) {
        Optional<Product> optionalProduct;
        if ((optionalProduct = findById(id)).isEmpty()) {
            return false;
        } else
            optionalProduct.get().setName(name);
        optionalProduct.get().setPrice(price);
        optionalProduct.get().setType(product.validateType(type));
        productRepository.save(optionalProduct.get());
        return true;
    }

    public Product updateName(int id, String name) {
        Optional<Product> optionalProduct = findById(id);
        if (optionalProduct.isEmpty()) {
            return null;
        } else {
            Product product = optionalProduct.get();
            product.setName(name);
            return productRepository.save(product);
        }
    }

    public boolean updateType(int id, String type) {
        Optional<Product> optionalProduct = findById(id);
        if (optionalProduct.isEmpty())
            return false;
        else
            optionalProduct.get().setType(product.validateType(type));
        productRepository.save(optionalProduct.get());
        return true;
    }

    public boolean delete(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}


