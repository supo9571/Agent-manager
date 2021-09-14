package com.manager.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.page.PageDomain;
import com.manager.common.core.page.TableDataInfo;
import com.manager.common.core.page.TableSupport;
import com.manager.common.utils.DateUtils;
import com.manager.common.utils.StringUtils;
import com.manager.common.utils.sql.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 *
 * @author marvin
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage(Integer page,Integer size,String orderByColumn,String isAsc) {
        page = (page == null) ? 1 : page;
        size = (size == null) ? 10 : size;
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(page);
        pageDomain.setPageSize(size);
        pageDomain.setOrderByColumn(orderByColumn);
        pageDomain.setIsAsc(isAsc);
        if(StringUtils.isNotBlank(orderByColumn) && StringUtils.isNotBlank(isAsc)){
            PageHelper.startPage(page, size, orderByColumn+" "+isAsc);
        }else {
            PageHelper.startPage(page, size);
        }

    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 设置排序
     */
    protected void startOrder(String orderByColumn,String isAsc) {
        if(StringUtils.isNotBlank(orderByColumn) && StringUtils.isNotBlank(isAsc)){
            PageHelper.orderBy(orderByColumn+" "+isAsc);
        }
    }

    /**
     * 设置排序 ，支持多个字段排序
     */
    protected void startOrders(String orderByColumns) {
        if(StringUtils.isNotBlank(orderByColumns)){
            PageHelper.orderBy(orderByColumns);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        PageInfo pageInfo = new PageInfo(list);
        TableDataInfo rspData = new TableDataInfo(pageInfo.getTotal(),list,pageInfo.getPageSize(),pageInfo.getPageNum());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows)
    {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success()
    {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error()
    {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message)
    {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message)
    {
        return AjaxResult.error(message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    protected HttpServletRequest getReuest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    protected String getHeader(String name){
        return getReuest().getHeader(name);
    }
}
