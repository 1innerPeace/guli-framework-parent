package com.guli.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.ucenter.entity.UcenterMember;
import com.guli.ucenter.mapper.UcenterMemberMapper;
import com.guli.ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-04-02
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.selectRegisterCount(day);
    }

    @Override
    public UcenterMember getByOpenid(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);

        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }
}
