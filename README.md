
<!--lang: java-->
####模仿的效果图：

![](https://github.com/ruzhan123/DoublePull/raw/master/gif/copy_gwl.gif)

</br>

我感觉我已经尽力了。

</br>

####格瓦拉实际效果图：

![](https://github.com/ruzhan123/DoublePull/raw/master/gif/gwl.gif)

</br>


我给这个控件取了个名字叫双层拖拽布局：DoublePull

这是一个比较复杂的控件，外层我使用了自定义ScrollView，可拖拽的子布局与大海报布局使用了Scroller来完成滑动。

里面那层是RecyclerView，RecyclerView滑动外层的ScrollView也跟着滑动，只是看不见而已。

---

1，事件首先由ScrollView获取和消耗，若滑倒顶部再往下滑，将事件传递给子View可拖拽子布局，并使用scrollBy来处理移动

2，若可拖拽布局移动过大，则向下隐藏。ScrollView不处理事件，让RecyclerView获取和消耗事件

3，RecyclerView可滑动时，点击底部打开按钮，可拖拽布局与大海报布局使用Scroller完成滑动，ScrollView又让获取到事件