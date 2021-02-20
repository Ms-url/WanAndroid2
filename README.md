# WanAndroid
* 项目只在wanAndroid包，其他为误传！

* 该APP使用的是wanAndroid开放API  

* 未使用MVP架构

# 分包说明：
* activity在 log_and_register包和 activitise包，其中登录和注册activity单独在 log_and_register包，其余activity在 activitise包； 

* fragment在 fragment包和 recyclerview包，其中 recyclerview包中为homepager活动的四个fragment中嵌套的二级fragment；  

* 适配器、数据类分别在 adapter和 dataclass包； 

* 网络请求，JSON解析类在 tools包

# 功能:

## 登录和注册

 登录成功后会将cookie储存在sharedpreference中，当sharedpreference中有数据时










