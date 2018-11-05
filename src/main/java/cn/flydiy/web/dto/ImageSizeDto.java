package cn.flydiy.web.dto;

import cn.flydiy.common.json.annotation.JsonIgnore;
import cn.flydiy.core.dto.BaseDto;

import javax.persistence.Transient;

/**
 * Created by player on 2017/6/16.
 */
public class ImageSizeDto extends BaseDto{

    @Transient
    @JsonIgnore
    private static final long serialVersionUID = -6716548333270645560L;

    private int width;

    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
