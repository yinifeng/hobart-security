package com.hobart.security.order.controller;

import com.hobart.security.order.model.dto.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    
    @GetMapping(value = "/r/r1")
    @PreAuthorize("hasAnyAuthority('p1')")//拥有p1权限方可访问次URl
    public String r1(){
        UserDTO principal = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getFullName() + "访问资源r1";
    }
}
