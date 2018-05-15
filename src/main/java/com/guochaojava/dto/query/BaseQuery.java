package com.guochaojava.dto.query;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseQuery implements Serializable {

    /**
     * 页码
     */
    @Builder.Default
    private Integer pageNum = 1;
    /**
     * 每页显示数量
     */
    @Builder.Default
    private Integer pageSize = 999999999;
}
