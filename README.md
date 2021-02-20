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

* banner下方的viewpager的两个页面为fragment，显示请求获得的文章和常用网站；
置顶文章会被标示出来，登录后已收藏的文章会显示红心；常用网站布局为scrollview包裹的横向瀑布流 

* 点击标题会将文章跳转到webActivity中打开

* 暂不支持点击item上的心形收藏，收藏与分享只能在webActivity中进行
### 体系
* 体系页面有知识体系和项目两页
#### 知识体系 
* 知识体系页为两个ListView ，可通过点击左方Listview索引右方内容
#### 项目 
* 项目item的图片为请求获取  
* 跳转顺序为 item → projectActivity or knowledgeActivity → webActivity
### 广场
* 广场会获取广场中的文章
* 如果没有登录，"我的分享″会提示登录，登录后会获取用户分享的文章
### 我的
* 点击退出登录会清除sharedpreference中的数据
* 点击其他的选项会跳转到相应界面
## 搜索 
* 为wanAndroid搜索接口
* 点击首页和体系的搜索框会跳转到同一搜索界面
* 搜索结果由两个轮换fragment显示 
* 点击取消会先隐藏搜索结果Fragment，再次点击会退出搜索界面
## 我的收藏 
* 我的收藏会拉取用户收藏的文章和网站以及用户的等级信息
## 我的积分 
* 我的积分会显示积分获取情况，和积分排行，未登录时会提示登录 
# 待提升的地方和心得体会
* 没有下拉刷新和向下载更多的功能 
* 主页面使用的是4个fragment切换，由于fragment中又嵌套了fragment，导致fragment过多，如果连续快速切换会造成闪退 ←_← →_→
* 收藏功能还没有写完 (T＿T)，目前不能取消收藏，不支持在item上直接收藏
* webview没有拦截功能，无法拦截恶意跳转的广告界面，有的网站因为自带scheme跳转而无法打开
* 没有写带头部的recycleview
* 界面有待改善









