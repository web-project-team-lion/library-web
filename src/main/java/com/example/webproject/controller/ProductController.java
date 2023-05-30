package com.example.webproject.controller;

import com.example.webproject.data.dto.ChangeProductNameDto;
import com.example.webproject.data.dto.ProductDto;
import com.example.webproject.data.dto.ProductResponseDto;
import com.example.webproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<ProductResponseDto> getProduct(Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping()
    public ResponseEntity<ProductResponseDto> changeProductName(
            @RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(
                changeProductNameDto.getNumber(),
                changeProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);

    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}