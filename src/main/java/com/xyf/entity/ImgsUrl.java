package com.xyf.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lmumuj
 * @date 2018-12-02 18:35
 */
public class ImgsUrl  implements Serializable {

    @NotNull
    private Integer id;
    @NotNull
    private String url;
    // 1:奖励说明图片地址  2:消费说明图片地址
    @NotNull
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
