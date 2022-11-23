package com.adminpro22.triger.controller.impl;

import com.adminpro22.triger.controller.ProductController;
import com.adminpro22.triger.dto.ProductDTO;
import com.adminpro22.triger.mapper.ProductMapper;
import com.adminpro22.triger.models.Product;
import com.adminpro22.triger.service.ProductService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/product")
@RestController
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductControllerImpl(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.asEntity(productDTO);
        return productMapper.asDTO(productService.save(product));
    }

    @Override
    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id).orElse(null);
        return productMapper.asDTO(product);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<ProductDTO> list() {
        return productMapper.asDTOList(productService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<ProductDTO> pageQuery(Pageable pageable) {
        Page<Product> productPage = productService.findAll(pageable);
        List<ProductDTO> dtoList = productPage
                .stream()
                .map(productMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, productPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductDTO productDTO, @PathVariable("id") Long id) {
        Product product = productMapper.asEntity(productDTO);
        return productMapper.asDTO(productService.update(product, id));
    }
}