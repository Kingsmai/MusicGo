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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MusicPlayerActivity extends AppCompatActivity {
    private TextView songName, singerName;
    private ImageView diskImage;
    private TextView lyricPrev, lyricCurrent, lyricNext;
    private SeekBar musicProgress;
    private TextView currentTime, totalTime;
    private ImageButton prevBtn, playPauseBtn, nextBtn;
    private ObjectAnimator animator;

    private boolean isPausing, isPlaying; // 音乐暂停状态，音乐第一次播放之后变为true

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
        lyricPrev = findViewById(R.id.tv_lyric_previous);
        lyricCurrent = findViewById(R.id.tv_lyric_current);
        lyricNext = findViewById(R.id.tv_lyric_next);
        musicProgress = findViewById(R.id.sb_progress);
        currentTime = findViewById(R.id.tv_progress_current);
        totalTime = findViewById(R.id.tv_progress_total);
        prevBtn = findViewById(R.id.btn_prev);
        playPauseBtn = findViewById(R.id.btn_play_pause);
        nextBtn = findViewById(R.id.btn_next);

        OnClickControl onClick = new OnClickControl();
        prevBtn.setOnClickListener(onClick);
        playPauseBtn.setOnClickListener(onClick);
        nextBtn.setOnClickListener(onClick);

        animator = ObjectAnimator.ofFloat(diskImage, "rotation", 0, 360.0F); // 初始化状态
        animator.setDuration(10000); // 转动时长，10秒
        animator.setInterpolator(new LinearInterpolator()); // 时间函数，有很多类型
        animator.setRepeatCount(-1); // 一直旋转
    }

    private class OnClickControl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_prev:
                    // 重播
                    Log.i("INFO", "onClick: 重播按钮被点击！");
                    playPauseBtn.setImageResource(R.drawable.control_pause_dark); // 切换成暂停键
                    animator.start();
                    break;
                case R.id.btn_play_pause:
                    // 开始暂停
                    Log.i("INFO", "onClick: 开始暂停按钮被点击！");
                    if (!isPausing && !isPlaying) { // 暂停状态，且从未被播放
                        // 开始播放
                        playPauseBtn.setImageResource(R.drawable.control_pause_dark); // 切换成暂停键
                        animator.start();
                        isPlaying = true;
                    } else if (!isPausing && isPlaying) { // 暂停状态，且被播放过一次
                        // 继续播放
                        playPauseBtn.setImageResource(R.drawable.control_pause_dark); // 切换成暂停键
                        animator.resume();
                    } else { // 播放状态
                        // 暂停播放
                        playPauseBtn.setImageResource(R.drawable.control_play_dark); // 切换成播放键
                        animator.pause();
                    }
                    isPausing = !isPausing; // 切换状态
                    break;
                case R.id.btn_next:
                    // 切歌
                    Log.i("INFO", "onClick: 切歌按钮被点击！");
                    break;
                default:
                    // 有Bug了
                    Log.i("INFO", "onClick: 按钮点击了，但是有BUG！");
            }
        }
    }
}