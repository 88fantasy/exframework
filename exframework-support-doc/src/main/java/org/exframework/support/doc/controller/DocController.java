package org.exframework.support.doc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.exframework.support.doc.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET})
@Tag(name = "文档")
public class DocController {

    @Autowired
    private DataBaseService dataBaseService;

    @Operation(summary = "获取数据库物理表(markdown)")
    @RequestMapping(value = "/database/md", method = RequestMethod.GET)
    public String markdown() {
        return dataBaseService.markdown();
    }

}
