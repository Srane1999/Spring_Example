package controller;


import models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/show",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products =productService.getAllProducts();
        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping(value = "/addProduct",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        ResponseEntity responseEntity;
        if (productService.addProduct(product))
        {
            responseEntity = new ResponseEntity<>("Added successfully",HttpStatus.OK);

        }
        else {
            responseEntity = new ResponseEntity<>("Not added",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/deleteProduct",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int productId){
        ResponseEntity responseEntity;
        if (productService.deleteProduct(productId))
        {
            responseEntity = new ResponseEntity<>("deleted ",HttpStatus.OK);
        }
        else {
            responseEntity = new ResponseEntity<>("Failed",HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
