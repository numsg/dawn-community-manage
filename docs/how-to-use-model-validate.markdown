# spring事务正确处理方式

## spring模型校验使用方式

我们现在是基于客户端与服务分离的开发模式，客户端向服务发送http请求携带的参数无法在客户端方面得到保证，那么我们就需要在服务端对客户端传递的实体参数进行校验。

推荐做法：使用spring模型校验模型。

使用步骤->

1. 在传输实体DTO上打注解
```
@ApiModel
public class AppUserInfo {

    @ApiModelProperty(hidden = true)
    private Long userId;

    @Size(min=1, max =31)
    @NotEmpty
    private String username;

    /**
     *
     */
    public AppUserInfo() {
    }

}
```

2. 在API上打注解
```
@RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
@ApiOperation(
        value = "add user",
        notes= "we can only accept the alarm information write with json!",
        response = AppUserController.class
)
public ResponseEntity handleUser( @RequestBody @Validated AppUserInfo appUserInfo, BindingResult bindingResult) {
    if(bindingResult.hasErrors()){
        throw new HttpRestException(HttpStatus.BAD_REQUEST, "param valiad failed");
    }
    return new ResponseEntity<>(userService.save(new AppUserInfo(appUserInfo.getUsername())), HttpStatus.OK);
}
```