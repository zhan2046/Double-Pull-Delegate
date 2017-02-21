
Double-Pull-Delegate
===============

A grace double layout pull delegate for Android

Screenshots
===============

![](https://github.com/ruzhan123/DoublePull/raw/master/gif/copy_gwl.gif)


Double-Pull-Delegate use **Scroller** and **Delegate**

[![](https://jitpack.io/v/ruzhan123/Double-Pull-Delegate.svg)](https://jitpack.io/#ruzhan123/Double-Pull-Delegate)

Gradle
------

Add it in your root build.gradle at the end of repositories:


```java

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency:


```java

	dependencies {
	        compile 'com.github.ruzhan123:Double-Pull-Delegate:v1.0'
	}
```


Usage
------

```xml

	<zhan.library.widget.PullScrollView 
		xmlns:android="http://schemas.android.com/apk/res/android"
	    android:id="@+id/main_root"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent">
	
	    <RelativeLayout
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content">
	
	        <zhan.library.widget.HeaderRelativeLayout
	            android:id="@+id/main_header"
	            android:layout_width="match_parent"
	            android:layout_height="275dp">
	
			...

			</zhan.library.widget.HeaderRelativeLayout>
	

	        <zhan.library.widget.BodyRelativeLayout
	            android:id="@+id/main_body"
	            android:layout_width="match_parent"
	            android:layout_height="wrap_content"
	            android:layout_marginTop="200dp">
	
			...
	
			 </zhan.library.widget.BodyRelativeLayout>
	    </RelativeLayout>
	</zhan.library.widget.PullScrollView>

		
```

Delegate
------

 <ul>
   	<li><a href='javascript:'>ScrollerDelegate</a></li>
   	<li><a href='javascript:'>ScrollHeaderDelegate</a></li>
	<li><a href='javascript:'>ScrollBodyDelegate</a></li>
	<li><a href='javascript:'>ScrollViewDelegate</a></li>
 </ul>

Implementing View
------

 <ul>
   	<li><a href='javascript:'>HeaderRelativeLayout</a></li>
   	<li><a href='javascript:'>HeaderFrameLayout</a></li>
	<li><a href='javascript:'>HeaderLinearLayout</a></li>
	<li><a href='javascript:'>BodyRelativeLayout</a></li>
	<li><a href='javascript:'>BodyFrameLayout</a></li>
	<li><a href='javascript:'>BodyLinearLayout</a></li>
	<li><a href='javascript:'>PullScrollView</a></li>
 </ul>

Custom
------

```java

	public class HeaderRelativeLayout extends RelativeLayout {
	
	    private ScrollHeaderDelegate mScrollHeaderDelegate;
	
	    public HeaderRelativeLayout(Context context) {
	        super(context);
	        init();
	    }
	
	    public HeaderRelativeLayout(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        init();
	    }
	
	    public HeaderRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
	        super(context, attrs, defStyleAttr);
	        init();
	    }
	
	    private void init() {
	        mScrollHeaderDelegate = new ScrollHeaderDelegate(this);
	    }
	
	    @Override
	    public void computeScroll() {
	        mScrollHeaderDelegate.computeScroll();
	    }
	
	
	    public void setScrollShow(boolean isScrollShow) {
	        mScrollHeaderDelegate.setScrollShow(isScrollShow);
	    }
	
	    public boolean isScrollShow() {
	        return mScrollHeaderDelegate.isScrollShow();
	    }
	
	    public void scrollShow() {
	        mScrollHeaderDelegate.scrollShow();
	    }
	
	    public void setDuration(int duration) {
	        mScrollHeaderDelegate.setDuration(duration);
	    }
	}

	...
```


Developed by
-------

 ruzhan - <a href='javascript:'>dev19921116@gmail.com</a>


License
-------

    Copyright 2017 ruzhan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
	
