package com.hwkj.search.controller;

import com.hwkj.search.bean.Knowledge;
import com.hwkj.search.bean.ProUpKonwledge;
import com.hwkj.search.bean.SearchParam;
import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.RestResponse;
import com.hwkj.search.common.RestResponses;
import com.hwkj.search.service.ILuceneService;
import com.hwkj.search.vo.HwkjSearchVo;
import com.hwkj.search.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * com.hwkj.search.controller
 *
 * @author Lenovo
 * @CreateTime 2020/9/15 17:14
 */
@RestController
@Slf4j
@CrossOrigin
public class LuceneController {

    @Autowired
    private ILuceneService luceneService;

    /**
     * 创建索引
     *
     * @param k 知识bean
     * @return
     */
    @PostMapping("/index")
    public RestResponse<String> creatIndex(@RequestBody List<Knowledge> k) {
        //根据文件path去服务器找文件信息
        try {
            luceneService.createIndex(k);
            return RestResponses.newSuccessResponse("索引创建成功", null);
        } catch (Exception e) {
            return RestResponses.newFailResponse(ErrorCode.INDEX_FAILURE, "文件被损坏" + e.getMessage());
        }
    }

    /**
     * 创建索引
     *
     * @param k 知识bean
     * @return
     */
    @PostMapping("/hwkjCreatIndex")
    public RestResponse<String> hwkjCreatIndex(@RequestBody List<Knowledge> k) {
        //根据文件path去服务器找文件信息
        try {
            luceneService.hwkjCreatIndex(k);
            return RestResponses.newSuccessResponse("索引创建成功", null);
        } catch (Exception e) {
            return RestResponses.newFailResponse(ErrorCode.INDEX_FAILURE, "文件被损坏" + e.getMessage());
        }
    }

    /**
     * 查询索引
     *
     * @param searchParam 前端传参
     * @return
     */
    @PostMapping("/search")
    public RestResponse<List<SearchVo>> search(@RequestBody(required = false) SearchParam searchParam) {
        try {
            List<SearchVo> result = luceneService.search(searchParam);
            return RestResponses.newSuccessResponse("查询完成", result);
        } catch (Exception e) {
            return RestResponses.newSuccessResponse(e.getMessage(), null);
        }
    }

    /**
     * 汉威科技查询索引
     *
     * @param searchParam 前端传参
     * @return
     */
    @PostMapping("/hwkjSearch")
    public RestResponse<List<HwkjSearchVo>> hwkjSearch(@RequestBody(required = false) SearchParam searchParam) {
        try {
            List<HwkjSearchVo> result = luceneService.hwkjSearch(searchParam);
            return RestResponses.newSuccessResponse("查询完成", result);
        } catch (Exception e) {
            return RestResponses.newSuccessResponse(e.getMessage(), null);
        }
    }

    /**
     * 更新索引
     * @param k
     * @return
     */
    @PostMapping("/updateIndex")
    public RestResponse<String> updateIndex(@RequestBody ProUpKonwledge k) {
        try {
            luceneService.updateIndex(k);
            return RestResponses.newSuccessResponse("更新成功",null);
        } catch (IOException e) {
            return RestResponses.newFailResponse(ErrorCode.INDEX_UPDATE_FAILURE,"索引更新失败");
        }
    }

    /**
     * 删除索引
     * @param id 文档id
     * @return
     */
    @GetMapping("/deleteIndex")
    public RestResponse<String> deleteIndex(@RequestParam String id){
        try {
            luceneService.deleteIndex(id);
            return RestResponses.newSuccessResponse("删除成功",null);
        } catch (Exception e) {
            return RestResponses.newFailResponse(ErrorCode.INDEX_DEL_FAILURE,"删除失败");
        }
    }
}
