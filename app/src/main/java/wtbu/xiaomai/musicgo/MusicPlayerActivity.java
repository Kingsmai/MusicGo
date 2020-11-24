package wtbu.xiaomai.musicgo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MusicPlayerActivity extends AppCompatActivity {

    private TextView songName, singerName;
    private ImageView diskImage;
    private ImageButton prevBtn, playPauseBtn, nextBtn;
    private boolean isPausing = false;
    private boolean isPlaying = false;
    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        init();
    }

    void init() {
        songName = findViewById(R.id.tv_song_name);
        singerName = findViewById(R.id.tv_song_singer);
        diskImage = findViewById(R.id.iv_disk);
        prevBtn = findViewById(R.id.btn_prev);
        playPauseBtn = findViewById(R.id.btn_play_pause);
        nextBtn = findViewById(R.id.btn_next);

        OnClick onClick = new OnClick();
        prevBtn.setOnClickListener(onClick);
        playPauseBtn.setOnClickListener(onClick);
        nextBtn.setOnClickListener(onClick);

        animator = ObjectAnimator.ofFloat(diskImage, "rotation", 0, 360F); // 初始化状态
        animator.setDuration(10000); // 转动时长,10秒
        animator.setInterpolator(new LinearInterpolator()); // 转动的时间函数,线性转动
        animator.setRepeatCount(-1); // 一直旋转
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_prev:
                    Log.i("INFO", "onClick: Previous button clicked!");
                    // 重播
                    animator.start(); // 图片重新转动
                    break;
                case R.id.btn_play_pause:
                    Log.i("INFO", "onClick: Play pause button clicked!");
                    if (!isPausing && !isPlaying) {
                        // 开始播放
                        playPauseBtn.setImageResource(R.drawable.control_pause_dark);
                        animator.start();
                    } else if (!isPausing && isPlaying) {
                        // 继续播放
                        playPauseBtn.setImageResource(R.drawable.control_pause_dark);
                        animator.resume();
                    } else {
                        // 暂停播放
                        playPauseBtn.setImageResource(R.drawable.control_play_dark);
                        animator.pause();
                    }
                    isPausing = !isPausing; // 取反
                    isPlaying = true; // 表示歌曲已经开始播放
                    break;
                case R.id.btn_next:
                    // 切歌
                    Log.i("INFO", "onClick: Next button clicked!");
                    break;
                default:
            }
        }
    }
}