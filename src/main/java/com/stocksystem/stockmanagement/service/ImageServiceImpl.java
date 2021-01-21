package com.stocksystem.stockmanagement.service;

import com.stocksystem.stockmanagement.Exception.FileStorageException;
import com.stocksystem.stockmanagement.model.Image;
import com.stocksystem.stockmanagement.model.Product;
import com.stocksystem.stockmanagement.repository.ImageRepository;
import com.stocksystem.stockmanagement.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageServiceImpl implements IImageService{

    final
    ProductRepository productRepository;
    final
    ImageRepository imageRepository;

    public ImageServiceImpl(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    private final String UPLOAD_DIR = "C:\\Users\\HP\\IdeaProjects\\codeshop\\src\\main\\resources\\static\\uploads";

    public void createImage(long productId, MultipartFile file){
        Image image = new Image();
        Product product = productRepository.findById(productId).get();
        image.setProduct(product);

        try {
            //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString();// Generate Universally Unique Identifier and convert to string...
            Path imagePath = Paths.get(UPLOAD_DIR + File.separator + fileName);
            Files.copy(file.getInputStream(),imagePath, StandardCopyOption.REPLACE_EXISTING);
            image.setUrl(fileName);

        } catch (IOException e) {
            e.printStackTrace();
            throw new FileStorageException("File Not Found");
        }
        imageRepository.save(image);
    }
}
