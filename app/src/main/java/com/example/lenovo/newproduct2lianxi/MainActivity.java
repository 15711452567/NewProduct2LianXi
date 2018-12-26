package com.example.lenovo.newproduct2lianxi;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView simple;
    private int iterations = 1;
    private int blurRadius = 10;
    private SimpleDraweeView simple_li;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Uri uri = Uri.parse("https://pic4.zhimg.com/03b2d57be62b30f158f48f388c8f3f33_b.png");



        showUrlBlur(simple, uri, iterations, blurRadius);

        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setBorder(R.color.colorAccent, 1);
        roundingParams.setRoundAsCircle(true);
        simple_li.getHierarchy().setRoundingParams(roundingParams);
        simple_li.setImageURI(uri);
    }

    public static void showUrlBlur(SimpleDraweeView draweeView, Uri url, int iterations, int blurRadius) {
        try {
            Uri uri = Uri.parse(String.valueOf(url));
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(draweeView.getController())
                    .setImageRequest(request)
                    .build();
            draweeView.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initView() {
        simple = (SimpleDraweeView) findViewById(R.id.simple);
        simple_li = (SimpleDraweeView) findViewById(R.id.simple_li);

    }
}
