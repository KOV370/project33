package com.JBProject3.controllerProduct;

import com.JBProject3.modelProduct.Product;
import com.JBProject3.serviceProduct.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    private ServiceProduct serviceProduct;
    private Product product;

    @Autowired
    private Controller(ServiceProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    @PostMapping("/create")
    private ResponseEntity<Product> create(@RequestBody Product product) {
        serviceProduct.create(product);
        if (product != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<Product>> findAll() {
        List<Product> products = serviceProduct.findAll();
        if (products != null || !products.isEmpty())
            return new ResponseEntity<>(products, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findById")
    private ResponseEntity<Product> findProductById(@RequestParam(value = "id") int id) {
        Optional<Product> product = serviceProduct.findById(id);
        if (!product.isEmpty()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByName")
    private ResponseEntity<List<Product>> findByName(@RequestParam("name") String name) {
        List<Product> products = serviceProduct.findByName(name);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/findByPrice")
    private ResponseEntity<List<Product>> findByPriceQuery(double price) {
        List<Product> products = serviceProduct.findByPriceLessThan(price);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByType")
    private ResponseEntity<List<Product>> findByType(@RequestParam(value = "type") String type) {
        List<Product> products = serviceProduct.findByType(type);
        if ((products == null) || products.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<?> update(@RequestParam("id") int id, @RequestParam("name") String name,
                                     @RequestParam("price") double price, @RequestParam("type") String type) {
        boolean isUpdated = serviceProduct.update(id, name, price, type);
        return isUpdated ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/updateName")
    private ResponseEntity<?> updateName(@RequestParam("id") int id,
                                         @RequestParam("name") String name) {
        boolean isUpdated = serviceProduct.updateName(id, name);
        return isUpdated ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PatchMapping("/updateType")
    private ResponseEntity<?> updateType(@RequestParam("id") int id,
                                         @RequestParam("type") String type) {
        boolean isUpdated = serviceProduct.updateType(id, type);
        return isUpdated ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/delete")
    private ResponseEntity<?> delete(@RequestParam("id") int id) {
        boolean isDeleted = serviceProduct.delete(id);
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}






