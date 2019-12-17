package com.smile.demo.swagger.Controller;

import com.smile.demo.swagger.model.HelloSwagger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = "Swagger Demo", value = "tags用于显示，value不会显示在页面上")
public class HelloSwaggerController {


    @ApiOperation(value = "方法的描述", notes = "方法说明")
    @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "path")
    @GetMapping("api/{name}")
    public HelloSwagger helloSwagger(@PathVariable("name") String name){
        return new HelloSwagger();
    }


    @PostMapping("put/form")
    @ResponseBody
    @ApiOperation(value = "插入数据/form")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "唯一标识", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "desc", value = "描述", required = false, dataType = "String", paramType = "query")
    })
    public void put(@RequestParam String id, @RequestParam String name, @RequestParam(required = false) String desc){

    }

    @PostMapping("put/json")
    @ResponseBody
    @ApiOperation(value = "插入数据/JSON")
    @ApiImplicitParam(name = "params", value = "参数体", dataType = "HelloSwagger", paramType = "body")
    public void putJson(@RequestBody HelloSwagger params){

    }


}
