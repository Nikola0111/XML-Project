package com.projekat.XML.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projekat.XML.dtos.ImageModelDTO;
import com.projekat.XML.model.ImageModel;
import com.projekat.XML.service.ImageModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.Iterator;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping(value = "imageModel")
public class ImageModelController {

    @Autowired
    ImageModelService imageModelService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, headers = "content-type!=multipart/form-data")
    @ResponseBody
    public ResponseEntity<ImageModel> uploadImage(@PathVariable("imageFile") MultipartFile file) throws IOException {
/*
        Iterator<String> iterator = request.getFileNames();
        MultipartFile mf = request.getFile(iterator.next());

        String fileName = mf.getOriginalFilename();
        System.out.println(fileName);
        String path=request.getServletContext().getRealPath("/");
        byte[] bytes = mf.getBytes();
        File directory=    new File(path+ "/images");
        directory.mkdirs();

        File file=new File(directory.getAbsolutePath()+System.getProperty("file.separator")+mf.getName());
        try {
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
         } catch (Exception e) {
            e.printStackTrace();
        }*/

        File img = new File("\\..\\..\\..\\resources\\images" + file.getOriginalFilename());
        img.createNewFile();

        System.out.println(img.getName());
        System.out.println(img.getPath());

        try(FileOutputStream fout = new FileOutputStream(img)) {
            fout.write(file.getBytes());
            System.out.println("Sacuvan");
        } catch (Exception e) {
            e.printStackTrace();
        }
        toJson(img);
        ImageModelDTO imageModelDTO = new ImageModelDTO(img.getName(), img.getPath());
        imageModelService.save(imageModelDTO);

         return new ResponseEntity<>(HttpStatus.OK);
}
/*
    @PostMapping(value="/uploadImage", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> uploadImage(@RequestBody ImageModelDTO imgDTO) {
        MultipartFile mf = (MultipartFile) file;

        ImageModel img = new ImageModel(mf.getOriginalFilename(), mf.getContentType(), compressBytes(mf.getBytes()));
        ImageModelDTO imageModelDTO = new ImageModelDTO(img.getName(), img.getType(), img.getPicByte());
        imageModelService.save(imageModelDTO);
        System.out.println(imgDTO.getName());
        imageModelService.save(imgDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    /*@GetMapping(value = "/getImage/{imageName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageModel getImage(@PathVariable("imageName") String imageName) {

        final Optional<ImageModel> retrievedImage =  imageModelService.findImage(imageName);
        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }*/

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    public String toJson(Object data)
    {
        ObjectMapper mapper=new ObjectMapper();
        StringBuilder builder=new StringBuilder();
        try {
            builder.append(mapper.writeValueAsString(data));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return builder.toString();
    }
}
