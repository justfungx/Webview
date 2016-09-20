package tw.org.iii.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mesg;
    private WebView webview;
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

        mesg = (TextView)findViewById(R.id.mesg);
        webview = (WebView)findViewById(R.id.webview);
        initWebView();
//      V1_END
    }

    private void initWebView(){
        WebViewClient client = new WebViewClient();
        webview.setWebViewClient(client);
      //  webview.loadUrl("http://www.iii.org.tw");

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        // ********************V4*************
        webview.loadUrl("file:///android_asset/mypage.html");

        // ********************END****************************


     //   ******************** V3 **********************
     //   String data = "<h1>Brad Big Company</h1>";
       // webview.loadData(data, "text/html;charset=UTF-8",null);

        //***********   END   *****     ****************
    }
    public void b1task(View v){
        doPrev();
    }
    public void b2task(View v){
        doNext();
    }
    public void b3task(View v){
        doReload();
    }

    private void doPrev(){
        webview.goBack();
    }
    private void doNext(){
        webview.goForward();
    }
    private void doReload(){
        webview.reload();
    }
}