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

* 可以游客进入，也可以注册登录，登录和注册时会弹出对话框提示用户等待，返回的错误信息会用toast弹出提示

* 账号和密码设置了长度限制，输入框会实时监听输入的长度，长度不够时，图标会变成红色

* 登录成功后会将cookie储存在sharedpreference中，当sharedpreference中有数据后进入App会直接从登录界面跳转至主界面，实现自动登录功能
## 主页面
主页面中放置了 首页、体系、广场、我的 四个fragment，可通过点击下方的按钮来切换
### 首页
* 首页顶部为一个banner，非网络请求图片，无点击效应，由viewpager充当，小圆点可指示位置

* banner下方的viewpager的两个页面为fragment，显示请求获得的文章和常用网站  
置顶文章会被标示出来，登录后已收藏的文章会显示红心;常用网站布局为scrollview包裹的横向瀑布流 










