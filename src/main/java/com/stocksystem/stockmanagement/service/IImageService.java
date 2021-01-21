package com.stocksystem.stockmanagement.service;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

    public void createImage(long productId, MultipartFile file);
}
