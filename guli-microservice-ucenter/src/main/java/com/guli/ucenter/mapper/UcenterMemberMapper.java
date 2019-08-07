package com.guli.ucenter.mapper;

import com.guli.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2019-04-02
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer selectRegisterCount(String day);
}
