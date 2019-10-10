package com.springboot.learning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:AjaxResult
 * @Description: TODO
 */
@Data
public class AjaxResult implements Serializable {

    private Object object;
    private int code;
    private String msg;

    public AjaxResult(Object o,int code){
        this.code = code;
        this.object = o;
    }


    public AjaxResult(String msg,int code){
        this.msg = msg;
        this.code = code;
    }

    public AjaxResult ok(){
        return new AjaxResult("成功！",200);
    }

    public AjaxResult error(){
        return new AjaxResult("服务器出错！",500);
    }

    public AjaxResult ok(Object oData){
        return new AjaxResult(oData,200);
    }

    public AjaxResult ok(List<Object> listData){
        return  new AjaxResult(listData,200);
    }

    public AjaxResult error(Object oData){
        return new AjaxResult(oData,500);
    }

    public AjaxResult error(List<Object> listData){
        return  new AjaxResult(listData,500);
    }

}
