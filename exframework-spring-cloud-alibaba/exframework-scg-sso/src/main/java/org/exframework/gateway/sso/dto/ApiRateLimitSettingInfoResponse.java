package org.exframework.gateway.sso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 系统限流设置信息响应
 *
 * @author ws
 * @date 2022/4/11 15:33
 **/
@ApiModel(value = "系统限流设置信息响应")
public class ApiRateLimitSettingInfoResponse {

    @ApiModelProperty(value = "限流接口清单")
    private List<String> apiIncludes;

    @ApiModelProperty(value = "滑动窗口时间：单位秒")
    private Integer SlidingWindowTime;

    @ApiModelProperty(value = "每个用户在滑动窗口内最大并发数")
    private Integer maxCount;

    public Integer getSlidingWindowTime() {
        return SlidingWindowTime;
    }

    public ApiRateLimitSettingInfoResponse setSlidingWindowTime(Integer slidingWindowTime) {
        SlidingWindowTime = slidingWindowTime;
        return this;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public ApiRateLimitSettingInfoResponse setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
        return this;
    }

    public List<String> getApiIncludes() {
        return apiIncludes;
    }

    public ApiRateLimitSettingInfoResponse setApiIncludes(List<String> apiIncludes) {
        this.apiIncludes = apiIncludes;
        return this;
    }
}
