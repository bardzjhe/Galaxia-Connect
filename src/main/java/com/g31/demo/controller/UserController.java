//package com.g31.demo.controller;
//
//import com.g31.demo.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @Description:
// */
//@RestController
//@RequestMapping("/users")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class UserController {
//
//    private final UserService userService;
//
//    @PostMapping("/sign-up")
//    @ApiOperation("用户注册")
//    public ResponseEntity<Void> signUp(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
//        userService.save(userRegisterRequest);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping
//    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
//    @ApiOperation("获取所有用户的信息（分页）")
//    public ResponseEntity<Page<UserRepresentation>> getAllUser(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//        Page<UserRepresentation> allUser = userService.getAll(pageNum, pageSize);
//        return ResponseEntity.ok().body(allUser);
//    }
//
//    @PutMapping
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @ApiOperation("更新用户")
//    public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
//        userService.update(userUpdateRequest);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    @ApiOperation("根据用户名删除用户")
//    public ResponseEntity<Void> deleteUserByUserName(@RequestParam("username") String username) {
//        userService.delete(username);
//        return ResponseEntity.ok().build();
//    }
//}
