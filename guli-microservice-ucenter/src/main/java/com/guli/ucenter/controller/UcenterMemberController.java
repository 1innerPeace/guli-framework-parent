package com.guli.ucenter.controller;


import com.guli.common.vo.R;
import com.guli.ucenter.service.UcenterMemberService;
import com.guli.ucenter.util.JwtUtils;
import com.guli.ucenter.vo.LoginInfoVo;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-04-02
 */
@CrossOrigin
@RestController
@RequestMapping("/ucenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    @ApiOperation(value = "今日注册数")
    @GetMapping(value = "/register/{day}")
    public R registerCount(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }

    @PostMapping("info/{token}")
    public R getInfoByToken(@PathVariable String token){

        Claims claims = JwtUtils.checkJWT(token);
        String nickname = (String)claims.get("nickname");
        String avatar = (String)claims.get("avatar");
        String id = (String)claims.get("id");
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        loginInfoVo.setId(id);
        loginInfoVo.setAvatar(avatar);
        loginInfoVo.setNickname(nickname);

        return R.ok().data("loginInfo", loginInfoVo);
    }

}

