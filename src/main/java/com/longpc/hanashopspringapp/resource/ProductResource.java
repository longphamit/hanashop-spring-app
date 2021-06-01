package com.longpc.hanashopspringapp.resource;

import com.longpc.hanashopspringapp.dto.BaseSearchParamDTO;
import com.longpc.hanashopspringapp.dto.ProductSearchParamDTO;
import com.longpc.hanashopspringapp.entities.ProductEntity;
import com.longpc.hanashopspringapp.entities.UserEntity;
import com.longpc.hanashopspringapp.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductResource extends BaseResource<ProductResource, ProductEntity> {
    @Autowired
    IProductService productService;
    @PostMapping("/search")
    public ResponseEntity search(@RequestBody ProductSearchParamDTO productSearchParamDTO){
        try{
            return responseListDataObject(HttpStatus.OK,Message.SEARCH_SUCCESS.name(),null);
        }catch (Exception e){
            e.printStackTrace();
            return responseListDataString(HttpStatus.INTERNAL_SERVER_ERROR,Message.SEARCH_FAIL.name(),null);
        }
    }
    @PostMapping
    public ResponseEntity insert(@RequestPart(value = "image" , required = false)  MultipartFile image,
                                 @RequestPart(value = "inforProduct") ProductEntity productEntity){
        try{
            productService.save(productEntity,image);
            List<ProductEntity> productEntityList= new ArrayList<>();
            productEntityList.add(productEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @PutMapping
    public ResponseEntity update(@RequestPart(value = "image" , required = false)  MultipartFile image,
                                 @RequestPart(value = "inforUser") UserEntity userEntity){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public ResponseEntity search() {
        return null;
    }

    @Override
    public ResponseEntity insert(ProductEntity productEntity) {
        return null;
    }

    @Override
    public ResponseEntity update(ProductEntity productEntity) {
        return null;
    }


}
