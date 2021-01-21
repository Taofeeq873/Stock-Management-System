package com.stocksystem.stockmanagement.controller;

import com.stocksystem.stockmanagement.service.ImageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

//@RestController
@Controller
public class ImageController {
    final ImageServiceImpl imageServiceImpl;

    public ImageController(ImageServiceImpl imageServiceImpl) {
        this.imageServiceImpl = imageServiceImpl;
    }

    //Add more Images to a product.
//    @CrossOrigin(exposedHeaders = "http://localhost:8888")
    @RequestMapping(value = "/images/add", method = RequestMethod.POST)
    public String addImages(Model model, @RequestParam long productId, @RequestParam("files") MultipartFile[] files){
        Arrays.asList(files).stream()
                .forEach(file -> imageServiceImpl.createImage(productId, file));

        return "redirect:/products/list";
    }
}
