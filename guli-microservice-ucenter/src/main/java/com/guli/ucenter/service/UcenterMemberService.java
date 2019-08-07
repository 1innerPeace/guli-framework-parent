package com.guli.ucenter.service;

import com.guli.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-04-02
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    Integer countRegisterByDay(String day);

    UcenterMember getByOpenid(String openid);

}
