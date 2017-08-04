package hlx.launch.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.KeyEvent;

/**
 * Created by admin on 2017/8/3.
 */

public class MCLauncherActivity143 extends Activity {


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK ){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("警告");
            builder.setMessage("你确定要退出游戏么?");
            builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.setNegativeButton("点错了", null);
            builder.create();
            builder.show();
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}
