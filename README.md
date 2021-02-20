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
* ![VIDEO_022014164972597](https://user-images.githubusercontent.com/73435804/108591477-f0b21c00-73a3-11eb-98d1-a08f1e231fe8.gif)

* 账号和密码设置了长度限制，输入框会实时监听输入的长度，长度不够时，图标会变成红色
* ![VIDEO_02201420335894](https://user-images.githubusercontent.com/73435804/108591432-b2b4f800-73a3-11eb-8879-d735d1fc1e40.gif)

* 登录成功后会将cookie储存在sharedpreference中，当sharedpreference中有数据后进入App会直接从登录界面跳转至主界面，实现自动登录功能

## 主页面
主页面中放置了 首页、体系、广场、我的 四个fragment，可通过点击下方的按钮来切换
* ![VIDEO_022014242949679](https://user-images.githubusercontent.com/73435804/108591542-3ec71f80-73a4-11eb-9a5e-5d18f58c2ff5.gif)

### 首页
* 首页顶部为一个banner，由viewpager充当，小圆点可指示位置
* ![VIDEO_022016381319852](https://user-images.githubusercontent.com/73435804/108591591-76ce6280-73a4-11eb-9faf-0d42ddce43a5.gif)

* banner下方的viewpager的两个页面为fragment，显示请求获得的文章和常用网站；
* 置顶文章会被标示出来
* 登录后已收藏的文章会显示红心；常用网站布局为scrollview包裹的横向瀑布流 
* ![VIDEO_022020124724414](https://user-images.githubusercontent.com/73435804/108594957-0af5f500-73b8-11eb-91d6-6cdee5dccbdd.gif)

* 点击标题会将文章跳转到webActivity中打开

* 暂不支持点击item上的心形收藏，收藏与分享只能在webActivity中进行
### 体系
* 体系页面有知识体系和项目两页
* ![VIDEO_022016535249638](https://user-images.githubusercontent.com/73435804/108593519-20b2ec80-73af-11eb-967a-49f9f0c2c551.gif)

#### 知识体系 
* 知识体系页为两个ListView ，可通过点击左方Listview索引右方内容
#### 项目 
* 项目item的图片为请求获取  
* 跳转顺序为 item → projectActivity or knowledgeActivity → webActivity
* 点击project link可进入该项目（github）
* ![VIDEO_022020285219776](https://user-images.githubusercontent.com/73435804/108595331-4d203600-73ba-11eb-9f8d-55d7e28ace61.gif)
* ![VIDEO_022020295977393](https://user-images.githubusercontent.com/73435804/108595463-f9fab300-73ba-11eb-84ff-c8247e211c2b.gif)


### 广场
* 广场会获取广场中的文章
* 如果没有登录，"我的分享″会提示登录，登录后会获取用户分享的文章
* ![VIDEO_02202031080348](https://user-images.githubusercontent.com/73435804/108595516-39290400-73bb-11eb-98c5-d20ce15f0fc2.gif)

### 我的
* 点击退出登录会清除sharedpreference中的数据
* 点击其他的选项会跳转到相应界面
* ![VIDEO_022016543285639](https://user-images.githubusercontent.com/73435804/108593833-227daf80-73b1-11eb-86ae-ef6dfe83bb2d.gif)
## 搜索 
* 为wanAndroid搜索接口
* 点击首页和体系的搜索框会跳转到同一搜索界面
* 搜索结果由两个轮换fragment显示 
* 点击取消会先隐藏搜索结果Fragment，再次点击会退出搜索界面
* ![VIDEO_022016545232712](https://user-images.githubusercontent.com/73435804/108593980-0a5a6000-73b2-11eb-9d0d-a577102ea96c.gif)
* ![VIDEO_022016545233290](https://user-images.githubusercontent.com/73435804/108594086-c6b42600-73b2-11eb-9cdb-947d89342c16.gif)

## 分享人文章列表
* 点击分享人可获取分享人分享的文章列表
* ![VIDEO_02202005463717](https://user-images.githubusercontent.com/73435804/108594813-1694ec00-73b7-11eb-832d-ec82e9d79473.gif)
* ![VIDEO_022020091976777](https://user-images.githubusercontent.com/73435804/108594891-98851500-73b7-11eb-9734-84c7a1385552.gif)

## 我的收藏 
* 我的收藏会拉取用户收藏的文章和网站以及用户的等级信息
* ![VIDEO_022020004780393](https://user-images.githubusercontent.com/73435804/108594714-8787d400-73b6-11eb-9693-481a25782384.gif)

## 我的积分 
* 我的积分会显示积分获取情况，和积分排行，未登录时会提示登录 
* ![VIDEO_022019465637050](https://user-images.githubusercontent.com/73435804/108594382-b8ffa000-73b4-11eb-88a1-9ef8f9e6bef0.gif)

# 待提升的地方和心得体会
* 没有下拉刷新和向下载更多的功能 
* 主页面使用的是4个fragment切换，由于fragment中又嵌套了fragment，导致fragment过多，如果连续快速切换会造成闪退 ←_← →_→
* 收藏功能还没有写完 (Ｔ▽Ｔ)，目前不能取消收藏，不支持在item上直接收藏
* webview没有拦截功能，无法拦截恶意跳转的广告界面，有的网站因为自带scheme跳转而无法打开
* 没有写带头部的recycleview
* 界面有待改善
## 心得体会
本来我想用 Material Design来着，结果cookie就卡了好几天，而且快过年的时候wanAndroid的登录接口不知道为啥不响应了，
让原本艰难的进程雪上加霜，最后好多功能和效果都没实现，虽然说有百度加持，
但很多百度上很多大佬都是直接用第三方库的，有的功能我实在找不到(￣ー￣)
，所以banner就直接用下好的图片用viewpager了，webview的拦截器也没写，
最后由于时间原因收藏功能也没有写完全，总地来说我最突出的感受就是，
太肝了(இωஇ )！！！(当然我也有很多的收获，学到了很多罒ω罒)








