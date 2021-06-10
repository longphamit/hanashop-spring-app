package com.longpc.hanashopspringapp.resource;

import com.longpc.hanashopspringapp.entities.BrandEntity;
import com.longpc.hanashopspringapp.services.IBrandService;
import com.longpc.hanashopspringapp.services.impl.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandResource extends BaseResource<BrandResource, BrandEntity> {
    @Autowired
    IBrandService brandService;

    @GetMapping
    @Override
    public ResponseEntity search() {
        return responseListDataObject(HttpStatus.OK, Message.SEARCH_SUCCESS.name(), brandService.search());
    }

    @PostMapping
    @Override
    public ResponseEntity insert(BrandEntity brandEntity) {
        return null;
    }
    @PutMapping
    @Override
    public ResponseEntity update(BrandEntity brandEntity) {
        return null;
    }

}
