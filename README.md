
<!--lang: java-->
####模仿的效果图：

![](https://github.com/ruzhan123/DoublePull/raw/master/gif/copy_gwl.gif)

</br>


####格瓦拉实际效果图：

![](https://github.com/ruzhan123/DoublePull/raw/master/gif/gwl.gif)

</br>



这是一个比较复杂的控件


我的博客：[详解](https://ruzhan123.github.io/2016/07/09/2016-07-09-03-%E4%BB%BF%E6%A0%BC%E7%93%A6%E6%8B%89%E5%8F%8C%E5%B1%82%E6%8B%96%E6%8B%BD%E5%B8%83%E5%B1%80%EF%BC%8C%E6%A0%BC%E7%93%A6%E6%8B%89%E7%94%B5%E5%BD%B1%E8%AF%A6%E6%83%85%E7%95%8C%E9%9D%A2/)


####简单分析：

1. 根部局使用了RelativeLayout，有两个子布局：外层布局与内层布局。
2. 外层布局。根布局为自定义ScrollView，有两子布局：HeaderFrameLayout与PullRelativeLayout
3. 内层布局。根部局为RelativeLayout，有两子布局：RecyclerView与ImageButton


外层布局如图：

![](https://github.com/ruzhan123/DoublePull/raw/master/gif/out.png)


内层布局如图：

![](https://github.com/ruzhan123/DoublePull/raw/master/gif/in.png)

---

####实现难点：

1.  初始化，事件由ScrollView处理和消耗。当滚动到顶部，若继续往下滑动，事件由子View PullRelativeLayout处理和消耗
2.  若PullRelativeLayout处理和消耗事件，拖拽距离过小，移动到原来位置。反之，则向下滑动隐藏布局
3.  HeaderFrameLayout随着PullRelativeLayout的变化而变化。隐藏则一起隐藏，打开则一起打开
4. PullRelativeLayout隐藏后，ScrollView将不能处理和消耗事件，事件应由RecyclerView处理和消耗
5. RecyclerView头布局滚动高度超过，头的70%，PullRelativeLayout做动画。HeaderFrameLayout做打开动画
6. HeaderFrameLayout自定义View内部需要有打开功能，可使用Scroller来完成
7.  PullRelativeLayout自定义View内部需要有打开和隐藏功能，可使用Scroller来完成
8. View的滑动也可使用动画的形式，但是由于需要设置一些Visibility属性，这里就使用Scroller来完成滑动
9. 自定义View之间的状态获取和数据交互，使用了对外提供回调接口的形式和对象直接注入的形式
10. 调试布局效果会花掉一些时间
