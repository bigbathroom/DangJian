package com.fw.dangjian.wheel.widget.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Property;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import com.fw.dangjian.R;


/*
 * 自定义View需要重写两个参数的构造
 * 并且调用super(context, attrs);方法
 * 为什么调用三个的构造却不行。
 * 事实证明调用三个的构造函数也是可以的 不过defStyleAttr必须设置为0 而不是-1；
 */
public class ButtonView extends View {
	int mWidth=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, this.getResources().getDisplayMetrics());
	int onBackground;
	int offBackground;
	int time;
	float layout_width;
	float layout_height;
	Paint mPaint;
	Scroller mScroller;
	float lastX;
	VelocityTracker mVelocity;
	ViewConfiguration mConfig;
	static final int RADIO=30;
	int circleX=0;
	public static boolean isOn;//是否开启了
	AnimatorSet set;
	int circleColor;
	int current_move;
	private Property<ButtonView, Integer> mColor=new Property<ButtonView, Integer>(Integer.class,"currentColor") {
		@Override
		public Integer get(ButtonView object) {
			return object.currentColor;
		}
		@Override
		public void set(ButtonView object, Integer value) {
			object.currentColor=value;
			invalidate();
		}
	};
	
	private Property<ButtonView, Integer> mMove=new Property<ButtonView, Integer>(Integer.class,"current_move") {
		@Override
		public Integer get(ButtonView object) {
			return object.current_move;
		}

		@Override
		public void set(ButtonView object, Integer value) {
			object.current_move=value;
		}
	};
	
	boolean isAnimatorStart=false;
	@SuppressWarnings("unchecked")
	public void startAnimator(int start,int end,int startColor,int endColor,int duration){
		set = new AnimatorSet();
		set.playTogether(ObjectAnimator.ofInt(this,mMove,start,end),ObjectAnimator.ofObject(this, mColor, new ArgbEvaluator(),startColor,endColor));
		set.setDuration(duration);
		set.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationStart(Animator animation) {
				isAnimatorStart=true;
			}
			@Override
			public void onAnimationEnd(Animator animation) {
				isAnimatorStart=false;
				if(changeListener!=null){
					changeListener.onChange(isOn);
				}
			}
		});
		set.setInterpolator(new DecelerateInterpolator());
		set.start();
	}
	
	public ButtonView(Context context) {
		this(context,null);
	}

	public ButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ButtonView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.buttonView);
		offBackground=a.getColor(R.styleable.buttonView_offBackground, Color.parseColor("#EDEDED"));
		onBackground=a.getColor(R.styleable.buttonView_onBackground, Color.parseColor("#FF4E4D"));
		time=a.getInteger(R.styleable.buttonView_time, 600);
		circleColor=a.getColor(R.styleable.buttonView_circleColor, Color.parseColor("#FFFFFF"));
		layout_height=a.getDimension(R.styleable.buttonView_layout_height, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics()));
		layout_width=a.getDimension(R.styleable.buttonView_layout_width, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, context.getResources().getDisplayMetrics()));
		mPaint=new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(offBackground);
		mPaint.setDither(true);
		mScroller=new Scroller(context);
		mConfig=ViewConfiguration.get(context);
	}

	@Override
	public void computeScroll() {
		if(mScroller.computeScrollOffset()){
			current_move=mScroller.getCurrX();
			invalidate();
		}
	}
	
	int min_move=circleX+RADIO;
	int max_move=0;
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int heightMode= MeasureSpec.getMode(heightMeasureSpec);
		int heightSize= MeasureSpec.getSize(heightMeasureSpec);
		int widthMode= MeasureSpec.getMode(widthMeasureSpec);
		int widthSize= MeasureSpec.getSize(widthMeasureSpec);
		int measureView_width = measureView(widthMode, widthSize, layout_width);
		int measureView_height= measureView(heightMode, heightSize, layout_height);
		//最大移动位置
		max_move=(int)(layout_width-RADIO);
		//最小移动位置
		setMeasuredDimension(measureView_width, measureView_height);
	}
	
	private int measureView(int mode,int size,float defaultSize){
		switch (mode) {
		case MeasureSpec.UNSPECIFIED:
		case MeasureSpec.AT_MOST:
			if(size>defaultSize){
				size=(int) Math.ceil(defaultSize);
			}
			return MeasureSpec.makeMeasureSpec(size,MeasureSpec.EXACTLY);
		case MeasureSpec.EXACTLY:
			return MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
		}
		return -1;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(isOn){// 绘制开关为开的状态
			currentColor = onBackground;
			current_move=max_move;
		}else{// 绘制开关为关的状态
			currentColor = offBackground;
			current_move=min_move;
		}
		mPaint.reset();
		mPaint.setAntiAlias(true);
		mPaint.setColor(currentColor);
		mPaint.setDither(true);
		canvas.drawRoundRect(new RectF(0, 0, layout_width, layout_height), RADIO, RADIO, mPaint);
		mPaint.setStyle(Style.STROKE);
		mPaint.setColor(Color.parseColor("#DADADA"));
		canvas.drawRoundRect(new RectF(0, 0, layout_width, layout_height), RADIO, RADIO, mPaint);
		mPaint.setStyle(Style.FILL);
		mPaint.setColor(circleColor);
		canvas.drawCircle(current_move, layout_height/2, layout_height/2-1, mPaint);
	}
	
	float x=0;
	float y=0;
	boolean isClick=false;
	Handler mHandler=new Handler();
	/**
	 * 当前颜色
	 */
	int currentColor;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			mHandler.removeCallbacks(clickRunable);
			if(isClick){
				if(isOn){//开启则关闭
					if(!isAnimatorStart){
						startAnimator(max_move, min_move, onBackground, offBackground, time);
						isOn=false;
					}
				}else{//否则开启
					if(!isAnimatorStart){
						startAnimator(min_move, max_move, offBackground, onBackground, time);
						isOn=true;
					}
				}
				if(click!=null){
					click.onClick(this);
				}
			}
			break;
		case MotionEvent.ACTION_DOWN:
			x=event.getX();
			y=event.getY();
			isClick=false;
			mHandler.postDelayed(clickRunable, mConfig.getScaledTouchSlop());
			break;
		case MotionEvent.ACTION_MOVE:
			float x=event.getX();
			float y=event.getY();
			isMove(x, y, mConfig.getScaledTouchSlop());
			break;
		default:
			break;
		}
		return true;
	}
	
	private void isMove(float x,float y,int touchSlop){
		if(Math.abs(this.x-x)>touchSlop||Math.abs(this.y-y)>touchSlop){
			isClick=false;
		}
	}
	
	private onChangeListener changeListener;
	private onClickListener click;
	
	public void setClick(onClickListener click) {
		this.click = click;
	}

	public void setChangeListener(onChangeListener changeListener) {
		this.changeListener = changeListener;
	}

	public interface onChangeListener{
		void onChange(boolean state);
	}
	
	public interface onClickListener{
		void onClick(View view);
	}
	
	ClickRunAble clickRunable=new ClickRunAble();
	
	class ClickRunAble implements Runnable{
		@Override
		public void run() {
			isClick=true;
		}
	}

}
