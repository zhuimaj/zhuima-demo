package com.zhuima.common;


import com.lianj.commons.beans.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Api(description = "用户操作接口")
@Controller("user")
@RequestMapping("/user")
public class UserController extends BasicController{

    @ApiOperation(value = "获取otp", notes="通过手机号获取OTP验证码")
    @ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "query", required = true, dataType = "Integer")
    @RequestMapping(value = "getotp", method= RequestMethod.GET)
    @ResponseBody
    public ResponseData<Object> getOtp(@RequestParam(name = "telephone") String telphone) {
        //需要按照一定的规则生成OTP验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);
        ResponseData<Object> result = respSuccess("操作成功");
        result.setData("scucess");
        return result;
/*
        //将OTP验证码同对应用户的手机号关,使用httpsession的方式绑定他的手机号与OTPCode
        httpServletRequest.getSession().setAttribute(telphone,otpCode);
        ResponseData<Long> result = respSuccess("采购计划新增成功");
        //将OTP验证码通过短信通道发送给用户，省略
        System.out.println("telphone = " + telphone + "& otpCode = " + otpCode);
        OtpVo otpVo = new OtpVo();
        otpVo.setTelephone(telphone);
        otpVo.setOtpCode(otpCode);
        return CommonReturnType.create(otpVo);*/
    }
}
