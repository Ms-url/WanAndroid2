# WanAndroid

该APP使用的是wanAndroid开放API  <Br/>
<Br/>
未使用MVP架构

# 分包说明：
activity在 log_and_register包和 activitise包，其中登录和注册activity单独在 log_and_register包，其余activity在 activitise包；  <Br/>
<Br/>
fragment在 fragment包和 recyclerview包，其中 recyclerview包中为主页四个fragment中嵌套的二级fragment；  <Br/>
<Br/>
适配器、数据类分别在 adapter和 dataclass包； <Br/>
<Br/>
网络请求，JSON解析类在 tools包； <Br/>













