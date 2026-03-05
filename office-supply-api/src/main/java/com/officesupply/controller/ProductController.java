package com.officesupply.controller;

import com.officesupply.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>(List.of(
        new Product(1,  "Ballpoint Pens (12-pack)",    "Writing",      4.99, 200),
        new Product(2,  "Mechanical Pencils (6-pack)", "Writing",      7.49, 150),
        new Product(3,  "Legal Pads (3-pack)",         "Paper",        6.99, 120),
        new Product(4,  "Copy Paper (500 sheets)",     "Paper",        9.99,  85),
        new Product(5,  "Sticky Notes (6-pack)",       "Paper",        5.49, 175),
        new Product(6,  "Stapler",                     "Fastening",   12.99,  60),
        new Product(7,  "Staples (1000-pack)",         "Fastening",    3.49, 300),
        new Product(8,  "Paper Clips (100-pack)",      "Fastening",    2.29, 400),
        new Product(9,  "Scissors",                    "Cutting",      6.99,  90),
        new Product(10, "Tape Dispenser with Tape",    "Adhesives",    8.49, 110),
        new Product(11, "Highlighters (5-pack)",       "Writing",      5.99, 160),
        new Product(12, "Dry Erase Markers (4-pack)",  "Writing",      7.99,  95),
        new Product(13, "3-Ring Binder (2-inch)",      "Organization", 9.49,  70),
        new Product(14, "File Folders (25-pack)",      "Organization", 8.99,  80),
        new Product(15, "Desk Organizer",              "Organization",19.99,  45)
    ));

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        return product.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        products.add(product);
        return ResponseEntity.status(201).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updated) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                updated.setId(id);
                products.set(i, updated);
                return ResponseEntity.ok(updated);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        return removed ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }
}
