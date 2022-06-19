package org.exframework.support.doc.controller;

import io.swagger.annotations.ApiOperation;
import org.exframework.support.doc.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocController {

    @Autowired
    private DataBaseService dataBaseService;

    @ApiOperation("获取数据库物理表(markdown)")
    @RequestMapping(value = "/database/md", method = RequestMethod.GET)
    public String markdown() {
        return dataBaseService.markdown();
    }

}
