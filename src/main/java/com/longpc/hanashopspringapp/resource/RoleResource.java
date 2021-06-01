package com.longpc.hanashopspringapp.resource;

import com.longpc.hanashopspringapp.constant.LoggerConstant;
import com.longpc.hanashopspringapp.entities.RoleEntity;
import com.longpc.hanashopspringapp.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/role")
public class RoleResource extends BaseResource<RoleResource, RoleEntity> {
    @Autowired
    IRoleService roleService;
    @GetMapping
    public ResponseEntity search(){
        try{
            getLogger(this).info(LoggerConstant.createMessageLog(Message.SEARCH_SUCCESS.name()));
            return responseListDataObject(HttpStatus.OK,Message.SEARCH_SUCCESS.name(),roleService.search());
        }catch (Exception e){
            e.printStackTrace();
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.SEARCH_FAIL.name(), null);
        }
    }
    @Override
    public ResponseEntity insert(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public ResponseEntity update(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public ResponseEntity update(MultipartFile file, RoleEntity roleEntity) {
        return null;
    }
}
