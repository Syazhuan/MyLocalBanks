package sg.edu.rp.c346.id22043300.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvDBS;
    TextView tvOCBC;
    TextView tvUOB;
    String wordClicked = "";
    String web_l = "";
    String con_l = "";
    String fav_l = "";
    String un_fav = "";
    String add_fav = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDBS = findViewById(R.id.textViewDBS);
        tvOCBC = findViewById(R.id.textViewOCBC);
        tvUOB = findViewById(R.id.textViewUOB);

        registerForContextMenu(tvDBS);
        registerForContextMenu(tvOCBC);
        registerForContextMenu(tvUOB);

        web_l = getString(R.string.web);
        con_l = getString(R.string.contact);
        fav_l = getString(R.string.fav);
        un_fav = getString(R.string.un_fav);
        add_fav = getString(R.string.add_fav);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, 0, 0, web_l);
        menu.add(0, 1, 1, con_l);
        menu.add(0, 2, 2, fav_l);

        if (v == tvDBS) {
            wordClicked = "DBS";
        } else if (v == tvOCBC) {
            wordClicked = "OCBC";
        } else if (v == tvUOB) {
            wordClicked = "UOB";
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.EnglishSelection) {
            tvDBS.setText("DBS");
            tvOCBC.setText("OCBC");
            tvUOB.setText("UOB");
            web_l = getString(R.string.web);
            con_l = getString(R.string.contact);
            fav_l = getString(R.string.fav);
            un_fav = getString(R.string.un_fav);
            add_fav = getString(R.string.add_fav);
            return true;
        } else if (id == R.id.ChineseSelection) {
            tvDBS.setText("星展银行");
            tvOCBC.setText("华侨银行");
            tvUOB.setText("大华银行");
            web_l = "网站";
            con_l = "联系银行";
            fav_l = "最喜欢的";
            un_fav = "已从收藏夹中删除";
            add_fav = "已添加到收藏夹";
            return true;
        } else {
            tvDBS.setText("");
            tvOCBC.setText("Error translation");
            tvUOB.setText("");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        if (wordClicked.equalsIgnoreCase("DBS")) {
            if (item.getItemId() == 0) { //check whether the selected menu item ID is 0
                //code for action
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.dbs_web)));
                startActivity(intent);
                return true; //menu item successfully handled
            } else if (item.getItemId() == 1) { //check if the selected menu item ID is 1
                //code for action
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + getString(R.string.dbs_no)));
                startActivity(intentCall);
                return true;  //menu item successfully handled
            } else if (item.getItemId() == 2) { //check if the selected menu item ID is 2
                //code for action
                toggleFavourite(tvDBS);
                return true;
            }
        } else if (wordClicked.equalsIgnoreCase("OCBC")) {
            if (item.getItemId() == 0) { //check if the selected menu item ID is 0
                //code for action
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.ocbc_web)));
                startActivity(intent);
                return true;  //menu item successfully handled
            } else if (item.getItemId() == 1) { //check if the selected menu item ID is 1
                //code for action
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + getString(R.string.ocbc_no)));
                startActivity(intentCall);
                return true;  //menu item successfully handled
            } else if (item.getItemId() == 2) { //check if the selected menu item ID is 2
                //code for action
                toggleFavourite(tvOCBC);
                return true;
            }
        } else if (wordClicked.equalsIgnoreCase("UOB")) {
            if (item.getItemId() == 0) { //check if the selected menu item ID is 0
                //code for action
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.uob_web)));
                startActivity(intent);
                return true;  //menu item successfully handled
            } else if (item.getItemId() == 1) { //check if the selected menu item ID is 1
                //code for action
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + getString(R.string.uob_no)));
                startActivity(intentCall);
                return true;  //menu item successfully handled
            } else if (item.getItemId() == 2) { //check if the selected menu item ID is 2
                //code for action
                toggleFavourite(tvUOB);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleFavourite(TextView tv) {
        if (tv.getCurrentTextColor() == Color.RED) {
            tv.setTextColor(Color.BLACK);
            Toast.makeText(MainActivity.this, un_fav, Toast.LENGTH_SHORT).show();
            if (fav_l == "Unfavourite") {
                fav_l = getString(R.string.fav);
            } else if (fav_l == "不喜欢") {
                fav_l = "最喜欢的";
            }
        } else {
            tv.setTextColor(Color.RED);
            Toast.makeText(MainActivity.this, add_fav, Toast.LENGTH_SHORT).show();
            if (fav_l == getString(R.string.fav)) {
                fav_l = "Unfavourite";
            } else if (fav_l == "最喜欢的") {
                fav_l = "不喜欢";
            }
        }
    }
}
