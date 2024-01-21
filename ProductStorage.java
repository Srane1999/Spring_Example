package dao;

import models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ProductStorage {

    private HashMap<Integer, Product> products = new HashMap<>();

    public ArrayList<Product> getAllProducts()
    {
        return new ArrayList<>(products.values());
    }

    public boolean addProduct(Product product)
    {
        products.put(product.getProductId(),product);
        return true;
    }

    public boolean updateProduct(int id, Product  product)
    {
        Product oldProduct = products.get(id);
        if (oldProduct!=null)
        {
            products.put(id,product);
            return true;
        }

        return false;
    }

    public boolean updateSingleProduct(int id, Product product)
    {
        Product oldProduct = products.get(id);
        if (oldProduct!=null)
        {
            if (product.getName()!=null)
            {
                oldProduct.setName(product.getName());
            }
            if (product.getPrice()!=0)
            {
                oldProduct.setPrice(product.getPrice());
            }
            products.put(id,oldProduct);
            return true;
        }
        return false;
    }

    public boolean deleteProduct(int id)
    {
        Product oldProduct = products.get(id);
        if (oldProduct!=null)
        {
            products.remove(id);
            return true;
        }
        return false;
    }
}
