# TrackTextView
- ### As依赖
在project目录下的build.gradle中

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

在app目录下的build.gradle中

    dependencies {
	        compile 'com.github.yanlongRivenK:TrackTextView:0.1'
	}
  
- ### xml中使用方式
  

       <com.yanlongrivenk.yltextview.TextViewYL
        android:layout_marginTop="50dp"
        android:layout_marginLeft="12dp"
        android:id="@+id/ttv"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="yanlongRivenK"
        android:textSize="40dp"
        app:originTvColor="#0f0"
        app:changeTvColor="#f30"
        app:Directionn="DIRECTION_RIGHT"
        app:percent="0.7"/>
     
- ###注意app自定义属性需要在布局根目录里添加命名空间
> xmlns:app="http://schemas.android.com/apk/res-auto"

- ### 代码中执行变色动画

      mTtv.setDirectionn(TextViewYL.Directionn.DIRECTION_LEFT);
      mTtv.startAnimator(2000);
      或者
      ObjectAnimator animator = ObjectAnimator.ofFloat(mTtv, "percent", 0, 1);
      animator.setDuration(10000).start();
      
 - ### 示例效果
 
 ![image](https://github.com/yanlongRivenK/TrackTextView/blob/master/GIF/tracktextview.gif)
      

      


