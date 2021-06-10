package com.longpc.hanashopspringapp.resource;
import com.longpc.hanashopspringapp.constant.ImageConstant;
import com.longpc.hanashopspringapp.dto.ProductSearchParamDTO;
import com.longpc.hanashopspringapp.entities.ProductEntity;
import com.longpc.hanashopspringapp.services.IImageService;
import com.longpc.hanashopspringapp.services.IProductService;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/product")
public class ProductResource extends BaseResource<ProductResource, ProductEntity> {
    @Autowired
    IProductService productService;
    @Autowired
    IImageService imageService;

    @PostMapping("/search")
    public ResponseEntity search(@RequestBody ProductSearchParamDTO productSearchParamDTO) {
        try {
            log(this, "CreateMessage", INFO);
            return responseListDataObject(HttpStatus.OK, Message.SEARCH_SUCCESS.name(), productService.search());
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataString(HttpStatus.INTERNAL_SERVER_ERROR, Message.SEARCH_FAIL.name(), null);
        }
    }

    @PostMapping
    public ResponseEntity insert(@RequestPart(value = "images", required = false) MultipartFile[] images,
                                 @RequestPart(value = "inforProduct") ProductEntity productEntity) {
        try {
            productService.save(productEntity, Arrays.asList(images));
            List<ProductEntity> productEntityList = new ArrayList<>();
            productEntityList.add(productEntity);
            return responseListDataObject(HttpStatus.OK, Message.CREATE_SUCCESS.name(), productEntityList);
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataString(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL.name(), null);
        }
    }

    @PostMapping("/ckfinder")
    @SneakyThrows
    public HashMap<String, Object> ckfinderImage(@RequestPart(value = "upload", required = false) MultipartFile image,
                                                 @RequestPart(value = "ckCsrfToken") String tokenCsrf) {
        String path= imageService.saveCkfinderImage(image);
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> mapSub = new HashMap<>();
        mapSub.put("acl",255);
        map.put("resourceType", "Images");
        map.put("fileName", image.getName());
        map.put("url",path);
        map.put("uploaded", 1);
        return map;
    }

    @GetMapping(path = "/ckfinder/image/{filename}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getCkfinderImage(@PathVariable("filename") String filename) throws Exception {
        return Files.readAllBytes(Paths.get(ImageConstant.PRODUCT_CKFINDER_IMAGE_FOLDER + "/" + filename));
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
