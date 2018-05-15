package com.guochaojava.dto.query;

import lombok.Data;

/**
 * @author guochao.
 * @since 1.0.0
 */
@Data
public class UserQuery extends BaseQuery{
    private String loginName;
    private String nickName;
}