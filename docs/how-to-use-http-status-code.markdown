# http状态码使用规范

## restful 服务返回http码使用规范

服务restful API规范设计应该严格参照[Gsafety REST API 设计规范](http://172.18.3.103/vNextDevTechs/Cooperation/blob/master/docs/Gsafety-REST-API-design-rules.md)，在这个规范里面其实规定了http响应码的使用。但在这里再强调一次

http状态码的职责是当客户端向服务器端发送请求时，描述请求返回结果。
我们要求http响应码返回能代表一定的意义，使得调用API方能够根据http状态码快速定位问题。

如下代码

```
if (reportParamModel != null) {
    reportRtnModels = personalApplyStatisticsanalysisService.getPersonalApplyStatisticsByProfessionAndPurpose(reportParamModel);
    return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
} else{
    return new ResponseEntity<>(reportRtnModels, HttpStatus.INTERNAL_SERVER_ERROR);
}
```
如果reportParamModel=null，服务返回内部500错误，这个显然不合适，这样返回，客户端调用API者会认为，这个错误是服务端的问题，客户端不需要作任何处理。实际上这个错误应该在客户端处理的，应该以http状态码400返回给客户端

正确使用方式如下，
```
if (reportParamModel != null) {
    reportRtnModels = personalApplyStatisticsanalysisService.getPersonalApplyStatisticsByProfessionAndPurpose(reportParamModel);
    return new ResponseEntity<>(reportRtnModels, HttpStatus.OK);
} else{
    return new ResponseEntity<>(reportRtnModels, HttpStatus.BAD_REQUEST);
}
```
当返回http状态码400，意义有：

1. 语义有误，当前的请求无法被服务器理解，除非进行修改，否则客户端不应该重复提交这个请求。
2. 请求参数有误。