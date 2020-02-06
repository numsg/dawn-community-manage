# spring事务正确处理方式

## 事务正确处理方式
在业务开发过程中经常会遇到在同一个业务或service中多数据库有多次记录操作，这时，spring中的事务发挥着重要作用。但在使用spring事务时，我们特别需要注意以下点。如下代码为例

```
AppUserEntity userEntity = userMapper.modelToEntity(appUserInfo);
userRepository.save(new AppUserEntity("nm11"));
int [] ints = new int[]{1,2,3,4};
System.out.println(ints[5]);
userRepository.save(new AppUserEntity("nm21"));
return userMapper.entityToModel(userRepository.save(userEntity));
```
如上代码，在发生异常时，事务不会默认回滚，为什么？ 在spring中,默认只有发生RuntimeException时，事务才会回滚。

另外如果我们把异常吃掉，事务同样不会回滚，如下代码
```
try {
    AppUserEntity userEntity = userMapper.modelToEntity(appUserInfo);
    userRepository.save(new AppUserEntity("nm11"));
    int [] ints = new int[]{1,2,3,4};
    System.out.println(ints[5]);
    userRepository.save(new AppUserEntity("nm21"));
    return userMapper.entityToModel(userRepository.save(userEntity));

}catch (Exception ex){
    return null;
}
```

正确处理方式有两种

1. 主动抛出RuntimeException：

```
try {
    AppUserEntity userEntity = userMapper.modelToEntity(appUserInfo);
    userRepository.save(new AppUserEntity("nm11"));
    int [] ints = new int[]{1,2,3,4};
    System.out.println(ints[5]);
    userRepository.save(new AppUserEntity("nm21"));
    return userMapper.entityToModel(userRepository.save(userEntity));

}catch (Exception ex){
    throw new RuntimeException();
}
```

2. 在catch中手动回滚事务

```
try {
    AppUserEntity userEntity = userMapper.modelToEntity(appUserInfo);
    userRepository.save(new AppUserEntity("nm11"));
    int [] ints = new int[]{1,2,3,4};
    System.out.println(ints[5]);
    userRepository.save(new AppUserEntity("nm21"));
    return userMapper.entityToModel(userRepository.save(userEntity));

}catch (Exception ex){
    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
}
```

另外在种子工程中，我们做了异常的切面处理，处理了Exception，而所有的异常都继承了Excetpion，只要有异常抛出，spring事务都会回滚，所以在事务处理中推荐的方式是，直接抛出异常即可。

`spring事务推荐处理方式如下`，

```
try {
    AppUserEntity userEntity = userMapper.modelToEntity(appUserInfo);
    userRepository.save(new AppUserEntity("nm11"));
    int [] ints = new int[]{1,2,3,4};
    System.out.println(ints[5]);
    userRepository.save(new AppUserEntity("nm21"));
    return userMapper.entityToModel(userRepository.save(userEntity));

}catch (Exception ex){
    throw new XXException();
}
```