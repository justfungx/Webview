package tw.org.iii.webview;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mesg;
    private WebView webview;
    private EditText inputName;
    private UIHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//     ********************* V2 **************************
//        webview = new WebView(this);
//        webview.loadUrl("http://www.iii.org.tw");
//        setContentView(webview);
//      V2_END

//     ********************* V1 **************************
        setContentView(R.layout.activity_main);

        mesg = (TextView) findViewById(R.id.mesg);
        webview = (WebView) findViewById(R.id.webview);
        inputName = (EditText) findViewById(R.id.inputName);
        handler = new UIHandler();

        initWebView();
//      V1_END
    }

    private void initWebView() {
        WebViewClient client = new WebViewClient();
        webview.setWebViewClient(client);
        //  webview.loadUrl("http://www.iii.org.tw");

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        webview.addJavascriptInterface(new MyJS(), "DK");

        // ********************V4*************
        webview.loadUrl("file:///android_asset/mypage.html");
        //   webview.loadUrl("file:///android_asset/images/ka.gif");
        // ********************END****************************


        //   ******************** V3 **********************
        //   String data = "<h1>Brad Big Company</h1>";
        // webview.loadData(data, "text/html;charset=UTF-8",null);

        //***********   END   *****     ****************
    }
    public class MyJS {
        @JavascriptInterface
        public void showMesg(String webmesg){
            Log.d("DK", webmesg);
            Toast.makeText(MainActivity.this, webmesg, Toast.LENGTH_SHORT).show();

            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("mesg", webmesg);
            msg.setData(data);
            handler.sendMessage(msg);
        }
        @JavascriptInterface
        public void gotoPage2(){
            Intent it = new Intent(MainActivity.this, Page2Activity.class);
            startActivity(it);
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mesg.setText(msg.getData().getString("mesg"));
        }
    }
        public void b1task(View v) {
            //doPrev();
            String name = inputName.getText().toString();
            webview.loadUrl("javascript:test2('" + name + "')");
        }

        public void b2task(View v) {
            int x = 3, y = 4;
            webview.loadUrl("javascript:test3(" + x + "," + y + ")");
            //doNext();
        }

        public void b3task(View v) {
            doReload();
        }

        private void doPrev() {
            webview.goBack();
        }

        private void doNext() {
            webview.goForward();
        }

        private void doReload() {
            webview.reload();
        }
    }
}