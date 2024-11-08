package test;

import main.Product;
import main.ProductManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductManagerTest {

    private ProductManager productManager;

    @BeforeEach
    public void setUp() {
        productManager = new ProductManager();
    }

    @Test
    public void testAddProductIncreasesCount() {
        Product product = new Product("Laptop", 1500.0);
        productManager.addProduct(product);
        assertEquals(1, productManager.getProductCount());
    }

    @Test
    public void testRemoveProductDecreasesCount() {
        Product product = new Product("Tablet", 500.0);
        productManager.addProduct(product);
        productManager.removeProduct("Tablet");
        assertEquals(0, productManager.getProductCount());
    }

    @Test
    public void testFindProductReturnsCorrectProduct() {
        Product product = new Product("Smartphone", 800.0);
        productManager.addProduct(product);
        Product foundProduct = productManager.findProduct("Smartphone");
        assertNotNull(foundProduct);
        assertEquals("Smartphone", foundProduct.getName());
    }

    @Test
    public void testFindProductReturnsNullIfNotFound() {
        Product product = new Product("Camera", 300.0);
        productManager.addProduct(product);
        Product foundProduct = productManager.findProduct("NonExistingProduct");
        assertNull(foundProduct);
    }

    @Test
    public void testRemoveProductReturnsTrueIfProductExisted() {
        Product product = new Product("Headphones", 100.0);
        productManager.addProduct(product);
        assertTrue(productManager.removeProduct("Headphones"));
    }

    @Test
    public void testRemoveProductReturnsFalseIfProductDidNotExist() {
        assertFalse(productManager.removeProduct("NonExistingProduct"));
    }

    @Test
    public void testAddMultipleProductsIncreasesCountCorrectly() {
        productManager.addProduct(new Product("Keyboard", 50.0));
        productManager.addProduct(new Product("Mouse", 25.0));
        assertEquals(2, productManager.getProductCount());
    }

    @Test
    public void testProductCountIsZeroInitially() {
        assertEquals(0, productManager.getProductCount());
    }

    @Test
    public void testAddingSameProductTwiceIncreasesCount() {
        Product product = new Product("Monitor", 200.0);
        productManager.addProduct(product);
        productManager.addProduct(product);
        assertEquals(2, productManager.getProductCount());
    }

    @Test
    public void testGetProductCountAfterRemovingNonExistingProduct() {
        productManager.addProduct(new Product("Printer", 150.0));
        productManager.removeProduct("NonExistingProduct");
        assertEquals(1, productManager.getProductCount());
    }
}

