package com.manager.common.core.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Entity基类
 *
 * @author marvin
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    @JsonIgnore
    private String searchValue;

    /**
     * 创建者
     */
    @ApiModelProperty("后台 创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("后台 创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    @ApiModelProperty("后台 更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("后台 修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 开始时间
     */
    @ApiModelProperty("搜索 开始时间 yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("搜索 结束时间 yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    /**
     * 备注
     */
    @JsonIgnore
    private String remark;

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("每页条数")
    private Integer size;

    @ApiModelProperty("排序字段")
    private String orderByColumn;

    @ApiModelProperty("排序方式 [asc：正序][desc：倒序]")
    private String isAsc;

    /**
     * 请求参数
     */
    @JsonIgnore
    private Map<String, Object> params;

    /**
     * 开始时间 ms
     */
    @JsonIgnore
    private String beginms;

    /**
     * 结束时间 ms
     */
    @JsonIgnore
    private String endms;

    public String getBeginms(){
        if(StringUtils.isNotBlank(this.beginTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(this.beginTime).getTime() + "";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return beginms;
    }

    public String getEndms(){
        if(StringUtils.isNotBlank(this.endTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(this.endTime).getTime() + "";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return endms;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
