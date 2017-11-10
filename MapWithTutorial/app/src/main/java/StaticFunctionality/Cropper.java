package StaticFunctionality;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/**
 * Created by Nick on 07.11.2017.
 */

public class Cropper {

    public static Bitmap cropCircleBitmap(Bitmap bitmap){
        bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
        int _width = bitmap.getWidth(), _height = bitmap.getHeight();
        Bitmap out = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        canvas.setBitmap(out);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        canvas.drawCircle(_width / 2, _height / 2, 50, paint);
        return out;
    }

    public static Bitmap cropCircleBitmap(int id, Context it){
        Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(it.getResources(),
                id), 100, 100, false);
        int _width = bitmap.getWidth(), _height = bitmap.getHeight();
        Bitmap out = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        canvas.setBitmap(out);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        canvas.drawCircle(_width / 2, _height / 2, 50, paint);
        return out;
    }

    public static BitmapDescriptor cropCircleBitmapDescriptor(Bitmap bitmap){
        bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
        int _width = bitmap.getWidth(), _height = bitmap.getHeight();
        Bitmap out = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        canvas.setBitmap(out);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        canvas.drawCircle(_width / 2, _height / 2, 50, paint);
        return BitmapDescriptorFactory.fromBitmap(out);
    }

    public static BitmapDescriptor cropCircleBitmapDescriptor(int id, Context it){
        Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(it.getResources(),
                id), 100, 100, false);
        int _width = bitmap.getWidth(), _height = bitmap.getHeight();
        Bitmap out = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        canvas.setBitmap(out);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        canvas.drawCircle(_width / 2, _height / 2, 50, paint);
        return BitmapDescriptorFactory.fromBitmap(out);
    }
}
