package com.heima.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.domain.User2;
import com.heima.properties.PersonProperties;
import com.heima.result.Result;
import com.heima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * xuan
 * 2018/2/2
 */
@Controller
public class UserController {

    @Autowired
    private PersonProperties personProperties;

    @Autowired
    private UserService userService;

    //    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    @GetMapping(value = "/findUser")
    public String findUser(@RequestParam(value = "id", required = false, defaultValue = "0") String id) {
        System.out.println(personProperties.getName());
        return "hello";
    }

    /**
     * 保存用户（代码校验）
     *
     * @param user 用户信息：包括name和age
     * @return Result对象
     */
    @PostMapping(value = "saveUser")
    @ResponseBody
    public Result saveUser(User2 user) {
        Result result = new Result();
        try {
            //校验用户名
            String name = user.getName();
            String name_regex = "[_a-zA-Z\\x{4e00}-\\x{9fa5}]{6,20}";    //下划线、中文和英文结合而且6-20字符
            if (name != null) {
                boolean name_match = name.matches(name_regex);
                if (!name_match) {
                    result.setCode(501);
                    result.setMessage("用户名不合法");
                    return result;
                }
            } else {
                result.setCode(501);
                result.setMessage("用户名不能为空");
                return result;
            }

            //校验年龄
            Integer age = user.getAge();
            if (age != null) {
                if (age > 150 || age <= 0) {
                    result.setCode(501);
                    result.setMessage("年龄不合法，应是1~150之间的整数");
                    return result;
                }
            } else {
                result.setCode(501);
                result.setMessage("年龄不能为空");
                return result;
            }

            //把id置为null，因为user表的id是自增的
            user.setId(null);
            userService.insertUser(user);
            result.setCode(200);
            result.setMessage("success");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("插入失败");
        }
        return result;
    }

    @PostMapping("/saveUser2")
    @ResponseBody
    public Result saveUser2(@Valid User2 user, BindingResult bindingResult) throws JsonProcessingException {
        boolean hasErrors = bindingResult.hasErrors();
        Result result = new Result();
        if (hasErrors) {
            result.setCode(500);
            ObjectMapper objectMapper = new ObjectMapper();
            result.setMessage(objectMapper.writeValueAsString(bindingResult.getAllErrors()));
            return result;
        }
        user.setId(null);
        userService.insertUser(user);
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<User2> findAll(User2 user) {
        System.out.println(321);
        return userService.findAll();
    }

    @GetMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam(value = "id",required = true) Integer id){
        Result result = new Result();
        try {
            userService.deleteUser(id);
            result.setCode(200);
            result.setMessage("success");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMessage("删除失败");
        }
        return result;
    }
}
