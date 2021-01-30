package com.hobart.security.service;

import com.hobart.security.domain.dto.PersonDTO;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MethdSpelService {

    /**
     *  @PreAuthorize("spel表达式")
     *  方法执行前 校验权限
     *  
     *  Authentication 认证信息中需要有admin角色
     *  否则抛出 AccessDeniedException
     *  
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    public List<PersonDTO> findAll(){
        SecurityContext context = SecurityContextHolder.getContext();
        return null;
    }

    /**
     * 
     * 方法执行完，返回执行权限校验
     * 
     * 返回对象returnObject的name == authentication的name
     * @return
     */
    @PostAuthorize("returnObject.name == authentication.name")
    public PersonDTO findOne(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);
        return new PersonDTO(name);
    }

    /**
     * 执行方法前过滤
     * 
     * 过滤掉参数ids % 2 == 0的数据
     * 
     * @param ids
     * @param userNames
     */
    @PreFilter(filterTarget="ids",value = "filterObject%2==0")
    public void delete(List<Integer> ids,List<String> userNames){
        System.out.println(ids);
    }

    /**
     * 方法执行完成过滤数据
     * 返回对象filterObject的name == 认证信息authentication的name
     * @return
     */
    @PostFilter(value = "filterObject.name == authentication.name")
    public List<PersonDTO> findAllFilter(){
        List<PersonDTO> list = new ArrayList<>();
        list.add(new PersonDTO("paul"));
        list.add(new PersonDTO("admin"));
        list.add(new PersonDTO("zhangsan"));
        return list;
    }
}
