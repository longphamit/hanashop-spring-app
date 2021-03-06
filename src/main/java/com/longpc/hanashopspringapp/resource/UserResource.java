package com.longpc.hanashopspringapp.resource;

import com.longpc.hanashopspringapp.constant.ImageConstant;
import com.longpc.hanashopspringapp.constant.LoggerConstant;
import com.longpc.hanashopspringapp.dto.LoginDTO;
import com.longpc.hanashopspringapp.dto.UserSearchParamDTO;
import com.longpc.hanashopspringapp.entities.UserEntity;
import com.longpc.hanashopspringapp.services.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource extends BaseResource<UserResource, UserEntity> {
    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public ResponseEntity loginByUsername(@RequestBody LoginDTO loginDTO) {
        List<UserEntity> userEntities = new ArrayList<>();
        try {
            UserEntity userResult = null;
            userResult = userService.loginByUsernameOrEmail(loginDTO.getLoginString(), loginDTO.getPassword());
            if (userResult == null) {
                log(this,"Login Fail",WARNING);;
                return responseListDataObject(HttpStatus.BAD_REQUEST, Message.LOGIN_BY_USERNAME_FAIL.name(), null);
            } else {
                userEntities.add(userResult);
                log(this,"Login Success"+loginDTO.getLoginString(),INFO);
                return responseListDataObject(HttpStatus.OK, Message.LOGIN_BY_USERNAME_SUCCESS.name(), userEntities);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.LOGIN_BY_USERNAME_FAIL.name(), null);
        }

    }

    @PostMapping("/login/email")
    public ResponseEntity loginByEmail(@RequestBody UserEntity userEntity) {
        try {
            if (StringUtils.isNotEmpty(userEntity.getEmail())) {
                userService.loginByEmail(userEntity.getEmail());
                List<UserEntity> userEntities = new ArrayList<>();
                userEntities.add(userEntity);
                log(this,"Login Success"+ userEntity.getEmail(),INFO);
                return responseListDataObject(HttpStatus.OK, Message.LOGIN_BY_USERNAME_SUCCESS.name(), userEntities);
            }
            log(this,"Login Fail",WARNING);
            return responseListDataObject(HttpStatus.BAD_REQUEST, Message.LOGIN_BY_USERNAME_FAIL.name(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.LOGIN_BY_USERNAME_FAIL.name(), null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register() {
        return new ResponseEntity("", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insert(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "fullname") String fullname,
            @RequestParam(value = "gender") String gender,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setEmail(email);
            userEntity.setFullname(fullname);
            userEntity.setGender(gender);
            userEntity.setPhone(phone);
            userService.createUser(userEntity, avatar);
            List<UserEntity> userEntities = new ArrayList<>();
            userEntities.add(userEntity);
            log(this,"Create User Success",INFO);
            return responseListDataObject(HttpStatus.OK, Message.CREATE_SUCCESS.name(), userEntities);
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL.name(), null);
        }
    }

    @Override
    public ResponseEntity search() {
        return null;
    }

    @Override
    public ResponseEntity insert(UserEntity userEntity) {
        return null;
    }

    @Override
    public ResponseEntity update(UserEntity userEntity) {
        return null;
    }

    @PutMapping
    public ResponseEntity update(
            @RequestPart(value = "image" , required = false)  MultipartFile avatar,
            @RequestPart(value = "inforUser") UserEntity userEntity) {
        try {
            userService.updateUser(userEntity, avatar);
            List<UserEntity> userEntities = new ArrayList<>();
            userEntities.add(userEntity);
            log(this,"Update User Success",INFO);
            return responseListDataObject(HttpStatus.OK, Message.CREATE_SUCCESS.name(), userEntities);
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.CREATE_FAIL.name(), null);
        }
    }

    @PostMapping("/search")
    public ResponseEntity search(@RequestBody UserSearchParamDTO userSearchParamDTO) {
        try {
            log(this,"Get List Users Success",INFO);
            return responseListDataObject(HttpStatus.OK, Message.SEARCH_SUCCESS.name(), userService.getUsers(userSearchParamDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return responseListDataObject(HttpStatus.INTERNAL_SERVER_ERROR, Message.SEARCH_FAIL.name(), null);
        }
    }

    @GetMapping(path = "/image/{username}/{filename}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getAvatar(@PathVariable("username") String username, @PathVariable("filename") String filename) throws Exception {
        return Files.readAllBytes(Paths.get(ImageConstant.USER_FOLDER + username + "/" + filename));
    }
}
