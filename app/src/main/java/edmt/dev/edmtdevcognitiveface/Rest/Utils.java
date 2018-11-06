package edmt.dev.edmtdevcognitiveface.Rest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import edmt.dev.edmtdevcognitiveface.Contract.Face;
import edmt.dev.edmtdevcognitiveface.Contract.FaceRectangle;

public class Utils {

    public static Bitmap drawFaceRectangleOnBitmap(Bitmap mBitmap, Face[] facesDetected,int color) {

        Bitmap bitmap = mBitmap.copy(Bitmap.Config.ARGB_8888,true);
        Canvas canvas = new Canvas(bitmap);
        //Rectangle
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(12);

        if(facesDetected != null)
        {
            for(Face face:facesDetected)
            {
                FaceRectangle faceRectangle = face.faceRectangle;
                canvas.drawRect(faceRectangle.left,
                        faceRectangle.top,
                        faceRectangle.left+faceRectangle.width,
                        faceRectangle.top+faceRectangle.height,
                        paint);

            }
        }
        return bitmap;
    }

    public static Bitmap drawFaceRectangleWithTextOnBitmap(Bitmap mBitmap, Face[] facesDetected, String name,int color,int textSize) {

        Bitmap bitmap = mBitmap.copy(Bitmap.Config.ARGB_8888,true);
        Canvas canvas = new Canvas(bitmap);
        //Rectangle
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(12);

        if(facesDetected != null)
        {
            for(Face face:facesDetected)
            {
                FaceRectangle faceRectangle = face.faceRectangle;
                canvas.drawRect(faceRectangle.left,
                        faceRectangle.top,
                        faceRectangle.left+faceRectangle.width,
                        faceRectangle.top+faceRectangle.height,
                        paint);
                drawTextOnCanvas(canvas,textSize,((faceRectangle.left+faceRectangle.width)/2)+textSize,(faceRectangle.top+faceRectangle.height)+textSize/2,Color.WHITE,name);

            }
        }
        return bitmap;
    }

    public static void drawTextOnCanvas(Canvas canvas, int textSize, int x, int y, int color, String name) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        paint.setTextSize(textSize);

        float textWidth = paint.measureText(name);

        canvas.drawText(name,x-(textWidth/2),y-(textSize/2),paint);
    }


}
